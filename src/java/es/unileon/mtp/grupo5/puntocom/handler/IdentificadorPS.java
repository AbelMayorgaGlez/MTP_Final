package es.unileon.mtp.grupo5.puntocom.handler;

import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import org.apache.log4j.Logger;

/**
 * 
 * Identificador concreto para los productos de subasta.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class IdentificadorPS implements Identificador {
	
	/**
	 * Numero del identificador.
	 */
	private int _numero;
	
	/**
	 * Digitos con los que se representa el numero.
	 */
	private static final int _MAX_DIGITOS = 6;
	
	/**
	 * Logger de la clase.
	 */
	static Logger _logger = Logger.getLogger(IdentificadorPS.class);
	
	public IdentificadorPS(){
		_numero = ContadorIdentificadores.siguienteNumero("subasta");
	}
	
	/**
	 * Constructor de clase a partir de un String.
	 * @param id String que representa al identificador que se desea construir.
	 * @throws BadFormatException si id no representa a un IdentificadorPS valido.
	 */
	public IdentificadorPS(String id) throws BadFormatException{
		id = id.trim(); //Elimina espacios en blanco del String
		String msg = "";
		if (id.length() != 9){
			msg += "La longitud debe ser 9.\n";
		} 
		if (id.substring(0,3).compareTo("PS-") != 0){
			msg += "El String debe identificarse como producto subasta comenzando por PS-.\n";
		}
		try{
			if (Integer.parseInt(id.substring(3)) <= 0){
				msg += "El numero del identificador debe ser mayor que 0.\n";
			}
		} catch (NumberFormatException e){
			msg += "El identificador debe terminar en un numero de 6 digitos.\n";
		}
		
		if (msg != ""){
			_logger.warn("Se ha intentado crear un IdentificadorPS a partir del String " + id);
			throw new BadFormatException(msg);
		}
		else {
			_numero = Integer.parseInt(id.substring(3));
		}
		_logger.info("IdentificadorPS creado con exito.");
	}
	
	/**
	 * Constructor de clase a partir de otro identificador.
	 * @param id Identificador a partir del cual se desea construir el actual.
	 * @throws BadFormatException si id no es un IdentificadorPS valido.
	 */
	public IdentificadorPS(Identificador id) throws BadFormatException{
		this(id.toString());
	}

	@Override
	public int compareTo(Identificador otro) {
		_logger.info("Comparando " + toString() + " con " + otro.toString());
		return toString().compareTo(otro.toString());
	}
	
	@Override
	public String toString(){
		String numero = Integer.toString(_numero);
		int cerosDeRelleno = _MAX_DIGITOS - numero.length();
		_logger.info("Se completa con " + cerosDeRelleno + " ceros para completar los " + _MAX_DIGITOS + "digitos.");
		for (;cerosDeRelleno > 0; cerosDeRelleno--){
			numero = "0" + numero;
		}
		return "PS-" + numero;
	}

}
