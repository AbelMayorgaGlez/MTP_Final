package es.unileon.mtp.grupo5.puntocom.decorator;

/**
 * ConcreteComponent del patron Decorator.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class Salida implements SalidaDecorada {
	
	/**
	 * Constructor de clase. Visibilidad de paquete.
	 */
	Salida(){}
	
	/**
	 * Escribe una linea por la salida estandar.
	 */
	@Override
	public void escribir(String linea) {
		System.out.println(linea);
	}
	
	@Override
	public void reiniciarSalida(){
	}

}
