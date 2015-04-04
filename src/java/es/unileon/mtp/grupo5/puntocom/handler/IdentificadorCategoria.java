package es.unileon.mtp.grupo5.puntocom.handler;

import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import org.apache.log4j.Logger;

/** 
 * 
 * Identificador concreto para las categorias.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class IdentificadorCategoria implements Identificador {

	/**
	 * Numero del identificador.
	 */
	private int _numero;
	/**
	 * Logger de la clase.
	 */
	static Logger _logger = Logger.getLogger(IdentificadorCategoria.class);
	
	public IdentificadorCategoria(){
		_numero = ContadorIdentificadores.siguienteNumero("categoria");
	}
	/**
	 * 
	 * Constructor de clase a partir de un String.
	 * 
	 * @param id String a partir del cual se construye el identificador.
	 * @throws BadFormatException si el String no representa un identificador valido.
	 */
	public IdentificadorCategoria(String id) throws BadFormatException{
		String msg = null;
		int n = 0;
		try{
			n = Integer.parseInt(id);
			if (n < 0) {
				msg = "El IdentificadorCategoria se debe crear a partir de un numero positivo.";
			}
		} catch (NumberFormatException e){
			msg = "El IdentificadorCategoria se debe crear a partir de un numero, que ademas debe ser positivo.";
		}
		
		if ( msg != null ){
			_logger.warn("Se ha intentado crear un IdentificadorCategoria a partir del String " + id);
			throw new BadFormatException(msg);
		} else {
			_numero = n;
			_logger.info("IdentificadorCategoria creado con exito");
		}
	}
	
	/**
	 * Constructor de clase a partir de otro identificador.
	 * @param id Identificador a partir del cual se construye el actual.
	 * @throws BadFormatException si el Identificador pasado no es un IdentificadorCategoria valido.
	 */
	public IdentificadorCategoria(Identificador id) throws BadFormatException{
		this(id.toString());
	}
	
	@Override
	public int compareTo(Identificador otro) {
		_logger.info("Comparando " + toString() + " con " + otro.toString());
		return toString().compareTo(otro.toString());
	}
	
	@Override
	public String toString(){
		return Integer.toString(_numero);
	}

}
