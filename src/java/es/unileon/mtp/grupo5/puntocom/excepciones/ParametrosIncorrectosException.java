package es.unileon.mtp.grupo5.puntocom.excepciones;

/** 
 * 
 * Excepcion que se lanza cuando un parametro de un metodo es incorrecto.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class ParametrosIncorrectosException extends Exception {

	/**
	 * Constructor de clase.
	 * @param msg mensaje que porta la excepcion.
	 */
	public ParametrosIncorrectosException(String msg){
		super(msg);
	}
}
