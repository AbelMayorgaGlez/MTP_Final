package es.unileon.mtp.grupo5.puntocom.excepciones;

/** 
 * 
 * Excepcion que se lanza cuando se intenta crear un Identificador a partir
 * de un String que no representa correctamente al Identificador.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class BadFormatException extends Exception {

	/**
	 * Constructor de clase.
	 * @param msg mensaje que porta la excepcion.
	 */
	public BadFormatException(String msg){
		super(msg);
	}
}
