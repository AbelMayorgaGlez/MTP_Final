package es.unileon.mtp.grupo5.puntocom.excepciones;

/**
 * 
 * Excepcion que se lanza cuando uno de los comandos no se ejecuta correctamente.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class ErrorEjecucionException extends Exception {

	/**
	 * Constructor de clase.
	 * @param msg mensaje que porta la excepcion.
	 */
	public ErrorEjecucionException(String msg){
		super(msg);
	}
}
