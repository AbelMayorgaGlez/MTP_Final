package es.unileon.mtp.grupo5.puntocom.excepciones;

/** 
 * 
 * Excepcion que se lanza cuando se intenta acceder a un metodo del composite
 * desde un objeto que no lo soporta.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class OperacionNoPermitidaException extends Exception {
	/**
	 * Constructor de clase.
	 * @param msg mensaje que porta la excepcion.
	 */
	public OperacionNoPermitidaException(String msg){
		super(msg);
	}

}
