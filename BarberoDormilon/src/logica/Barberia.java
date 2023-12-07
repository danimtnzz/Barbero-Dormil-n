package logica;

//import java.awt.Panel;

import javax.swing.JOptionPane;

import ui.panelBarberia;

public class Barberia {
	
	private int nSillasEspera = 0;
	private int nSillasEsperaOcupadas = 0;
	private boolean sillaBarberoOcupada = false;
	private boolean finAfeitado = false;
	private boolean barberoDormido = false;
	private panelBarberia PanelB;
	private int afeitados = 0;
	
	//JAVA: solo puede haber N_Sillas_Espera_max esperando dentro del monitor a que le toque
	
	public Barberia(int nSillasEspera, panelBarberia PanelB) {
		this.nSillasEspera = nSillasEspera;
		this.PanelB = PanelB;
	}
	
	public synchronized boolean llegaCliente(int clienteId)throws InterruptedException{
		if (nSillasEsperaOcupadas==nSillasEspera) {
			//Si no hay sillas libres, me voy sin afeitar
			JOptionPane.showMessageDialog(null, "El cliente "+clienteId+" se va sin afeitarse");
//			System.out.println("El cliente "+clienteId+" se va sin afeitar");
			PanelB.getLabelInfo().setText("El cliente "+clienteId+" se va sin afeitarse");
			return false;
		}else {
			//El cliente se queda esperando si la silla del barbero esta ocupada
			nSillasEsperaOcupadas++;
			PanelB.ocuparSillasEspera(nSillasEsperaOcupadas);
			System.out.println("El cliente "+clienteId+" se sienta en la silla de espera");
			//esperar a que quede libre la silla del barbero
			while(sillaBarberoOcupada) {
				wait();
			}
			//si el barbero esta dormido le despierto
			if (barberoDormido) {
				System.out.println("El cliente "+clienteId+" despierta al barbero");
				PanelB.adaptarImagen(PanelB.getBarberoDurmiendo(), "img/barbero.jpg");
				notifyAll();
			}
			///---------------proceso de afeitar al cliente--------------------
			//Desocupo la silla de espera
			nSillasEsperaOcupadas--;
			PanelB.ocuparSillasEspera(nSillasEsperaOcupadas);
			//Me siento en la silla del barbero
			sillaBarberoOcupada = true;
			finAfeitado = false;
			//Espero a que me corte el pelo
			System.out.println("El cliente "+clienteId+" en la silla del barbero");
			
			
			
			PanelB.adaptarImagen(PanelB.getSillonBarbero(), "img/cliente.jpg");
			while (!finAfeitado) {
				wait();
				Thread.sleep(1000);//tiempo del afeitado
			}
			sillaBarberoOcupada=false;
			PanelB.adaptarImagen(PanelB.getSillonBarbero(), "img/sillonbarbero.jpg");
			notify();
			notifyAll();//despierta a los hilos que esten esperando, en este caso para salir
			//del bucle de finAfeitado
			afeitados++;
			PanelB.actualizaClientes(afeitados);
			System.out.println("--- El cliente "+clienteId+" se va con afeitado");
			return true;
		}//fin del else
	}//fin del llegacliente
	public synchronized void esperarCliente()throws InterruptedException{
		//El barbero espera a que llegue un cliente
		
		barberoDormido = true;
		while (!sillaBarberoOcupada) {//mientras no se haya sentado un cliente
			System.out.println("Barbero esperando cliente");
			wait();
		}
		barberoDormido = false;
		System.out.println("Barbero afeitando a un cliente");
	}
	public synchronized void acabarAfeitado() {
		finAfeitado = true;
		System.out.println("Barbero termina de afeitar a un cliente");
		notifyAll();//para pasar al siguiente cliente
	}
	
}
