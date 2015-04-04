package es.unileon.mtp.grupo5.puntocom.decorator;

import java.io.IOException;

/**
 * Decorator del patron Decorator.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public abstract class Decorador implements SalidaDecorada {

	/**
	 * Salida a la que decora.
	 */
	protected SalidaDecorada _decorado;
	
	/**
	 * Constructor de clase. Visibilidad de paquete.
	 * @param decorado
	 */
	Decorador(SalidaDecorada decorado){
		_decorado = decorado;
	}
	
	@Override
	public abstract void escribir(String linea) throws IOException;

	@Override
	public abstract void reiniciarSalida() throws IOException;
}
