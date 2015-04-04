package es.unileon.mtp.grupo5.puntocom.handler;

import org.apache.log4j.Logger;

import es.unileon.mtp.grupo5.puntocom.excepciones.*;

/**
 * 
 * Identificador concreto para los productos de venta.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class IdentificadorPV implements Identificador {
	
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
	static Logger _logger = Logger.getLogger(IdentificadorPV.class);
	
	public IdentificadorPV(){
		_numero = ContadorIdentificadores.siguienteNumero("venta");
	}
	
	/**
	 * Constructor de clase a partir de un String.
	 * @param id String que representa al identificador que se desea construir.
	 * @throws BadFormatException si id no representa a un IdentificadorPV valido.
	 */
	public IdentificadorPV(String id) throws BadFormatException{
		id = id.trim(); //Elimina espacios en blanco del String
		String msg = "";
		if (id.length() != 9){
			msg += "La longitud debe ser 9.\n";
		} 
		if (id.substring(0,3).compareTo("PV-") != 0){
			msg += "El String debe identificarse como producto subasta comenzando por PV-.\n";
		}
		try{
			if (Integer.parseInt(id.substring(3)) <= 0){
				msg += "El numero del identificador debe ser mayor que 0.\n";
			}
		} catch (NumberFormatException e){
			msg += "El identificador debe terminar en un numero de 6 digitos.\n";
		}
		
		if (msg != ""){
			_logger.warn("Se ha intentado crear un IdentificadorPV a partir del String " + id);
			throw new BadFormatException(msg);
		}
		else {
			_numero = Integer.parseInt(id.substring(3));
		}
		_logger.info("IdentificadorPV creado con exito.");
	}
	
	/**
	 * Constructor de clase a partir de un identificador.
	 * @param id Identificador a partir del cual se desea construir el actual.
	 * @throws BadFormatException si id no es un IdentificadorPV valido.
	 */
	public IdentificadorPV(Identificador id) throws BadFormatException{
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
		return "PV-" + numero;
	}
}

