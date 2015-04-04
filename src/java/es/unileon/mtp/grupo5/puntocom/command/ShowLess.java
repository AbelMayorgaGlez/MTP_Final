package es.unileon.mtp.grupo5.puntocom.command;

import java.io.IOException;

import org.apache.log4j.Logger;

import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.composite.Elemento;
import es.unileon.mtp.grupo5.puntocom.decorator.SalidaSingleton;
import es.unileon.mtp.grupo5.puntocom.excepciones.ParametrosIncorrectosException;
import es.unileon.mtp.grupo5.puntocom.iterator.Iterador;

/**
 * 
 * Comando concreto. Lista todos los productos del composite con un precio menor al indicado.
 * 
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class ShowLess implements Comando {
	/**
	 * Tienda.
	 */
	private Tienda _tienda;
	/**
	 * Array de Strings
	 */
	private String[] _parametros;
	/**
	 * Precio.
	 */
	float _precio;
	/**
	 * Log de la clase.
	 */
	static Logger _logger = Logger.getLogger(ShowLess.class);
	/**
	 * Constructor
	 * @param tienda
	 * 				Tienda.
	 * @param parametros
	 * 				Array de Strings que contiene la informacion referente al producto.
	 * 				En este caso es el precio.
	 * @throws ParametrosIncorrectosException
	 */
	public ShowLess(Tienda tienda, String[] parametros) throws ParametrosIncorrectosException{
		_parametros=parametros;
		_tienda=tienda;
		String msg="";
		if(_parametros.length!=1){
			msg += "Error de sintaxis. Sintaxis correcta: SHOWLESS precio";			
		} else {
			try{
				_precio = Float.parseFloat(parametros[0]);
			} catch (NumberFormatException e){
				msg += "El precio debe ser numerico";
			}
		}
		if(!msg.equals("")){
			_logger.warn("No se han introducido bien los parametros");
			throw new ParametrosIncorrectosException(msg);
		}
	}

	@Override
	public void ejecutar() throws IOException {	
		Iterador<Elemento> itr = _tienda.darIterador(Float.toString(_precio));
		_logger.info("Elementos con un precio menor a: " + _precio);
		SalidaSingleton.getSalida().escribir("Elementos con un precio menor a: "+_precio);
		while(itr.haySiguiente()){
			SalidaSingleton.getSalida().escribir(itr.elementoActual().getNombre());
			_logger.info(itr.elementoActual().getNombre());
		}

	}

}
