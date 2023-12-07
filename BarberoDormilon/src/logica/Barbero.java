package logica;

public class Barbero extends Thread{
	private Barberia barberia=null;
	public Barbero(Barberia barberia) {
		this.barberia=barberia;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				barberia.esperarCliente();//recibe un cliente
				Thread.sleep(1000);
				barberia.acabarAfeitado();//realiza la accion de afeitar
				Thread.sleep(1000);//se pone a dormir
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}
