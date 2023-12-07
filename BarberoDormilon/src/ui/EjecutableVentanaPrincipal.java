package ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.jvnet.substance.SubstanceLookAndFeel;

import logica.Barberia;
import logica.Barbero;
import logica.Cliente;

public class EjecutableVentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private panelBarberia panelB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EjecutableVentanaPrincipal frame = new EjecutableVentanaPrincipal();
					frame.setSize(900, 700);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public EjecutableVentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		panelB = new panelBarberia();
//		panelB.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelB);
		Barberia barberia = new Barberia(panelB.getSillas(), panelB);
		Barbero barbero = new Barbero(barberia);
		
		barbero.start();
		
		for (int i = 0; i < panelB.getClientes(); i++) {
			Cliente c = new Cliente(barberia, i+1);
			c.start();
		}
	}

}
