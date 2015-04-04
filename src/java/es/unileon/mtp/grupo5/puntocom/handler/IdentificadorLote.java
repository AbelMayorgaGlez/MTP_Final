package es.unileon.mtp.grupo5.puntocom.handler;

import org.apache.log4j.Logger;
import es.unileon.mtp.grupo5.puntocom.excepciones.BadFormatException;

/** 
 * 
 * Identificador concreto para los lotes.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class IdentificadorLote implements Identificador{
	
	/**
	 * Numero del identificador.
	 */
	private int _numero;
	
	/**
	 * Logger de la clase.
	 */
	static Logger _logger = Logger.getLogger(IdentificadorLote.class);
	
	public IdentificadorLote(){
		_numero = ContadorIdentificadores.siguienteNumero("lote");
	}
	/**
	 * 
	 * Constructor de clase a partir de  un Sting.
	 * @param id String a partir del cual se construye el identificador.
	 * @throws BadFormatException si el String no representa un identificador valido.
	 */
	public IdentificadorLote(String id) throws BadFormatException{
		String msg = "";
		int n = 0;
		if(id.substring(0,3).compareTo("LT-")!=0){
			msg += "El string debe empezar por LT-.\n";
		}
		try{
			n = Integer.parseInt(id.substring(3));
			if (n < 0) {
				msg += "El IdentificadorLote se debe crear a partir de un numero positivo.";
			}
		} catch (NumberFormatException e){
			msg += "El IdentificadorLote se debe crear a partir de un numero, que ademas debe ser positivo.";
		}
		
		if ( msg.compareTo("") != 0 ){
			_logger.warn("Se ha intentado crear un IdentificadorLote a partir del String " + id);
			throw new BadFormatException(msg);
		} else {
			_numero = n;
			_logger.info("IdentificadorLote creado con exito");
		}
	}
	
	/**
	 * Constructor de clase a partir de un identificador.
	 * @param id Identificador a partir del cual se construye el actual.
	 * @throws BadFormatException si el Identificador pasado no es un IdentificadorLote valido.
	 */
	public IdentificadorLote(Identificador id) throws BadFormatException{
		this(id.toString());
	}

	@Override
	public int compareTo(Identificador otro) {
		_logger.info("Comparando " + toString() + " con " + otro.toString());
		return toString().compareTo(otro.toString());
	}
	
	@Override
	public String toString(){
		return "LT-" + Integer.toString(_numero);
	}

}


