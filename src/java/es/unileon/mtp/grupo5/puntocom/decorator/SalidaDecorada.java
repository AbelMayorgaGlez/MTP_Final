package es.unileon.mtp.grupo5.puntocom.decorator;

import java.io.IOException;

/**
 * Interfaz Component del patron Decorator.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public interface SalidaDecorada {

	/**
	 * Escribe un string.
	 * @param linea String a escribir.
	 * @throws IOException Si no se puede escribir.
	 */
	void escribir(String linea) throws IOException;
	
	/**
	 * Reinicia la salida.
	 * @throws IOException Si falla la reapertura.
	 */
	void reiniciarSalida() throws IOException;
}
