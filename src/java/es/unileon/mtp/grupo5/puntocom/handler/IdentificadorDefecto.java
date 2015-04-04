package es.unileon.mtp.grupo5.puntocom.handler;

import org.apache.log4j.Logger;

/** 
 * 
 * Identificador concreto por defecto.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class IdentificadorDefecto implements Identificador {

	/**
	 * String que representa el identificador.
	 */
	private String _id;
	/**
	 * Logger de la clase.
	 */
	static Logger _logger = Logger.getLogger(IdentificadorDefecto.class);
	
	/**
	 * Constructor de clase a partir de un String.
	 * @param id String que representa al identificador.
	 */
	public IdentificadorDefecto(String id){
		_id = id;
		_logger.info("IdentificadorDefecto creado con exito.");
	}
	
	/**
	 * Constructor de clase a partir de otro identificador.
	 * @param otro
	 */
	public IdentificadorDefecto(Identificador otro){
		_id = otro.toString();
	}
	
	@Override
	public int compareTo(Identificador otro) {
		_logger.info("Comparando " + toString() + " con " + otro.toString() + ".");
		return toString().compareTo(otro.toString());
	}
	
	@Override
	public String toString(){
		return _id;
	}

}
