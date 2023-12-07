package ui;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jvnet.substance.SubstanceLookAndFeel;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Component;

public class panelBarberia extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel principal;
	private JPanel panel_barberia;
	private JPanel panel_clientes;
	private JPanel panel_sillas;
	private JPanel panel_zonaBarbero;
	private JLabel SillonBarbero;
	private JLabel BarberoDurmiendo;
	private JLabel labelInfo;
	private int sillas = 0;	
	private int clientes = 0;
	
	
	public int getSillas() {
		return sillas;
	}


	public int getClientes() {
		return clientes;
	}
	/**
	 * Create the panel.
	 */
	public panelBarberia() {
		setLayout(new BorderLayout(0, 0));
		add(getPanel_1(), BorderLayout.NORTH);
		add(getPanel_1_1(), BorderLayout.CENTER);
		String respuesta = JOptionPane.showInputDialog("Introducir numero de sillas");
		sillas = Integer.parseInt(respuesta);
		creaSillas(sillas);
		String respuesta2 = JOptionPane.showInputDialog("Introducir numero de clientes");
		clientes = Integer.parseInt(respuesta2);
		creaClientes(clientes);
//		JFrame.setDefaultLookAndFeelDecorated(true);
//		JDialog.setDefaultLookAndFeelDecorated(true);
//		
//		SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.BusinessBlackSteelSkin");
	}
	private JPanel getPanel_1() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getLabelInfo());
		}
		return panel;
	}
	private JPanel getPanel_1_1() {
		if (principal == null) {
			principal = new JPanel();
			principal.setLayout(new GridLayout(0, 2, 0, 0));
			principal.add(getPanel_barberia());
			principal.add(getPanel_clientes());
		}
		return principal;
	}
	private JPanel getPanel_barberia() {
		if (panel_barberia == null) {
			panel_barberia = new JPanel();
			panel_barberia.setLayout(new GridLayout(2, 0, 0, 0));
			panel_barberia.add(getPanel_sillas());
			panel_barberia.add(getPanel_zonaBarbero());
		}
		return panel_barberia;
	}
	private JPanel getPanel_clientes() {
		if (panel_clientes == null) {
			panel_clientes = new JPanel();
		}
		return panel_clientes;
	}
	private JPanel getPanel_sillas() {
		if (panel_sillas == null) {
			panel_sillas = new JPanel();
			panel_sillas.setLayout(new GridLayout(1, 1, 0, 0));
		}
		return panel_sillas;
	}
	private JPanel getPanel_zonaBarbero() {
		if (panel_zonaBarbero == null) {
			panel_zonaBarbero = new JPanel();
			panel_zonaBarbero.setLayout(new GridLayout(0, 2, 50, 50));
			panel_zonaBarbero.add(getSillonBarbero());
			panel_zonaBarbero.add(getBarberoDurmiendo());
		}
		return panel_zonaBarbero;
	}
	private void creaSillas(int sillas) {
		for(int i = 0; i < sillas; i++) {
			JLabel silla = new JLabel();
			silla.setSize(50, 50);
			adaptarImagen(silla, "img/silla.jpg");
			panel_sillas.add(silla);
		}
	}

	private void creaClientes(int clientes) {
		for(int i = 0; i < clientes; i++) {
			JLabel cliente = new JLabel();
			cliente.setSize(50, 50);
			adaptarImagen(cliente, "img/clienteespera.png");
			panel_clientes.add(cliente);
		}
	}
	public void adaptarImagen(JLabel boton, String rutaImagen) {
		Image imgOriginal = null;
		try {
			imgOriginal = new ImageIcon(rutaImagen).getImage();
		} catch (Exception e) {
			imgOriginal = new ImageIcon(rutaImagen).getImage();
		}
		Image imgCarrito = imgOriginal.getScaledInstance((int) (boton.getWidth()), (int) (boton.getHeight()),
				Image.SCALE_FAST);

		boton.setIcon(new ImageIcon(imgCarrito));

		boton.setDisabledIcon(new ImageIcon(imgCarrito));
	}
	public JLabel getSillonBarbero() {
		if (SillonBarbero == null) {
			SillonBarbero = new JLabel("");
			SillonBarbero.setSize(50,50);
			//FIXME: cambiar imagen
			
			
			adaptarImagen(SillonBarbero, "img/sillonbarbero.jpg");
		}
		return SillonBarbero;
	}
	public JLabel getBarberoDurmiendo() {
		if (BarberoDurmiendo == null) {
			BarberoDurmiendo = new JLabel("");
			BarberoDurmiendo.setSize(50, 50);
			adaptarImagen(BarberoDurmiendo, "img/barberodurmiendo.jpg");

		}
		return BarberoDurmiendo;
	}
	public JLabel getLabelInfo() {
		if (labelInfo == null) {
			labelInfo = new JLabel("Barbería de Daniel");
		}
		return labelInfo;
	}
	public void ocuparSillasEspera(int nSillasEsperaOcupadas) {
		// TODO Auto-generated method stub
		Component []componentes = panel_sillas.getComponents();
		for (int i = 0; i < componentes.length; i++) {
			JLabel silla = (JLabel) componentes[i];
			adaptarImagen(silla, "img/silla.jpg");
		}
		for (int i = 0; i < nSillasEsperaOcupadas; i++) {
			JLabel silla = (JLabel) componentes[i];
			adaptarImagen(silla, "img/paisano_sentado.jpg");
		}
	}
	public void actualizaClientes(int nSillasEsperaOcupadas) {
		// TODO Auto-generated method stub
		Component []componentes = panel_clientes.getComponents();
		for (int i = 0; i < componentes.length; i++) {
			JLabel silla = (JLabel) componentes[i];
			adaptarImagen(silla, "img/clienteespera.png");
		}
		for (int i = 0; i < nSillasEsperaOcupadas; i++) {
			JLabel silla = (JLabel) componentes[i];
			adaptarImagen(silla, "img/clienteafeitado.jpg");
		}
	}
}