package logica;

public class Cliente extends Thread{
	private int idCliente;
	private Barberia barberia = null;
	
	public Cliente(Barberia barberia, int numeroCliente) {
		this.idCliente = numeroCliente;
		this.barberia = barberia;
	}
	
	@Override
	public void run() {
		boolean afeitado = false;
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(3000);
				afeitado = barberia.llegaCliente(idCliente);
				//Afeitarse
				if (afeitado) {
					System.out.println("El cliente "+idCliente+" se ha afeitado");
				}
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
	public int getNumeroCliente(){
		return idCliente;
	}
	
	public void setNumeroCliente(int numeroCliente) {
		this.idCliente=numeroCliente;
	}
}
