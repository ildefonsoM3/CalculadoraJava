package graficos;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Calculadora {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Ventana calcu = new Ventana();

	}

}

class Ventana extends JFrame {
	
	public Ventana() {
		setTitle("Calculadora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(90, 30, 450, 300);
		Toolkit pantalla = Toolkit.getDefaultToolkit();
		Image icon = pantalla.createImage("src/graficos/img/piña.png");
		setIconImage(icon);
		
		PanelCalculadora display = new PanelCalculadora();
		
		add(display);
		//pack();//Toma el tamaño ajustado de los componenetes
		
		setVisible(true);
	}
}

class PanelCalculadora extends JPanel {
	
	private JPanel botonesCalcu;
	private JButton pantallaCalcu;
	private boolean primerNumero;//Para saber si se pulsó por primera vez una tecla 
	
	public PanelCalculadora() {
		
		primerNumero = true;//Pone la variable true para el if de la clase InsertaNumero
		
		//PANTALLA
		setLayout(new BorderLayout());
		
		pantallaCalcu = new JButton("0");
		pantallaCalcu.setEnabled(false);//Inhabilita que se pueda presionar el botón
		
		add(pantallaCalcu, BorderLayout.NORTH);		
		
		
		//BOTONES
		botonesCalcu = new JPanel();
		botonesCalcu.setLayout(new GridLayout(4, 4));
		add(botonesCalcu, BorderLayout.CENTER);
		
		ActionListener insertar = new InsertaNumero();/*Instancia que pasa el segundo
		argumento de ActionListener oyente en la clase PonerBoton()*/
		
		PonerBoton("7", insertar);
		PonerBoton("8", insertar);
		PonerBoton("9", insertar);
		//PonerBoton("MAS");
		
		PonerBoton("4", insertar);
		PonerBoton("5", insertar);
		PonerBoton("6", insertar);
		//PonerBoton("MENOS");
		
		PonerBoton("1", insertar);
		PonerBoton("2", insertar);
		PonerBoton("3", insertar);
		//PonerBoton("POR");
		
		PonerBoton("0", insertar);
		PonerBoton(".", insertar);
		//PonerBoton("CE");
		//PonerBoton("=");
		
	}
	
	private void PonerBoton(String rotulo, ActionListener oyente) {
		
		JButton boton = new JButton(rotulo);//Poner el Botón
		boton.addActionListener(oyente);/*Se crea el evento y pone a todos los 
		botones a la escucha*/
		
		botonesCalcu.add(boton);//Se agrega al panel	
	}
	
	private class InsertaNumero implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			String entrada = e.getActionCommand();/*Se agrega el texto que se ha
			generado el evento del botón*/
			
			//Quita el primer Cero para poder ir agregando números a la pantalla
			if (primerNumero) {
				pantallaCalcu.setText(" ");
				primerNumero = false;
			}
			pantallaCalcu.setText(pantallaCalcu.getText() + entrada);//Se agrega a la pantalla
			
		}
		
	}
}












