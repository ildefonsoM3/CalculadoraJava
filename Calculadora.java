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
	private double resultado;
	private String ultimaOperacion;
	
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
		ActionListener orden = new AccionOrder();
		
		PonerBoton("7", insertar);
		PonerBoton("8", insertar);
		PonerBoton("9", insertar);
		PonerBoton("MAS", orden);
		
		PonerBoton("4", insertar);
		PonerBoton("5", insertar);
		PonerBoton("6", insertar);
		PonerBoton("MENOS", orden);
		
		PonerBoton("1", insertar);
		PonerBoton("2", insertar);
		PonerBoton("3", insertar);
		PonerBoton("POR", orden);
		
		PonerBoton("0", insertar);
		PonerBoton(".", insertar);
		PonerBoton("CE", orden);
		PonerBoton("=", orden);
		
		ultimaOperacion = "=";
		
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
	
	private class AccionOrder implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			//Se obtiene el botón de operación
			String operacion = e.getActionCommand();
			
			Calcular(Double.parseDouble(pantallaCalcu.getText()));	
			
			ultimaOperacion = operacion;//Almacena las operaciones que se vayan realizando					
			
			/*Hace que borre el siguiente valor numérico cuando se hace una operación
			que se pasa por parámetro cuando un botón de operación se presiona
			y al entrar en el if de la clase de InsertaNumero resetea el display */
			primerNumero = true;
			
		}
		
		public void Calcular(double x) {
			
			if (ultimaOperacion.equals("MAS"))
				resultado+= x;
			else if (ultimaOperacion.equals("MENOS"))
				resultado -= x;
			else if (ultimaOperacion.equals("POR"))
				resultado *= x;
			else if (ultimaOperacion.equals("CE"))
				resultado = 0;
			else if (ultimaOperacion.equals("="))
				resultado = x;
			
			pantallaCalcu.setText("" + resultado);
			
		}
		
	}
}












