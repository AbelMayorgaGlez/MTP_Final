package es.unileon.mtp.grupo5.puntocom.decorator;

import java.io.*;

/**
 * ConcreteDecorator del patron Decorator.
 * Escribe por fichero.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class AFichero extends Decorador {

	/**
	 * Fichero del que lee.
	 */
	private BufferedWriter _fichero;
	
	private String _nombreFichero;
	
	/**
	 * Constructor de clase. Visibilidad de paquete.
	 * @param decorado Salida a la que decora.
	 * @param nombreFichero Nombre del fichero de salida.
	 * @throws IOException
	 */
	AFichero(SalidaDecorada decorado, String nombreFichero) throws IOException{
		super(decorado);
		_fichero = new BufferedWriter(new FileWriter(nombreFichero));
		_nombreFichero = nombreFichero;
	}
	
	/**
	 * Escribe una linea en el fichero.
	 */
	@Override
	public void escribir(String linea) throws IOException {
		_fichero.write(linea);
		_fichero.newLine(); //Introduce un salto de linea.
		_fichero.flush(); //Manda el buffer al fichero.
		_decorado.escribir(linea);
	}
	
	@Override
	public void reiniciarSalida() throws IOException{
		_fichero.close();
		_fichero = new BufferedWriter(new FileWriter(_nombreFichero));
		_decorado.reiniciarSalida();
	}

}
