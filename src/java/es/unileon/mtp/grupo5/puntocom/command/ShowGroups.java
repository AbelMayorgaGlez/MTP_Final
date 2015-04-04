package es.unileon.mtp.grupo5.puntocom.command;

import java.io.IOException;

import org.apache.log4j.Logger;

import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.composite.Elemento;
import es.unileon.mtp.grupo5.puntocom.decorator.SalidaSingleton;
import es.unileon.mtp.grupo5.puntocom.iterator.Iterador;

/**
 * 
 *Comando concreto. Lista todas las categorias del composite.
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
public class ShowGroups implements Comando {
	/**
	 * Tienda
	 */
	private Tienda _tienda;
	/**
	 * Log de la clase
	 */
	static Logger _logger = Logger.getLogger(ShowGroups.class);
	/**
	 * Contructor
	 * @param tienda
	 * 				Tienda.
	 */
	public ShowGroups(Tienda tienda){
		_tienda=tienda;
	}

	@Override
	public void ejecutar() throws IOException {
		Iterador<Elemento> itr=_tienda.darIterador("categorias");
		_logger.info("Elementos que son categorias:");
		SalidaSingleton.getSalida().escribir("Categorias que hay actualmente creadas:");
		while(itr.haySiguiente()){
			SalidaSingleton.getSalida().escribir(itr.elementoActual().getNombre());
			_logger.info(itr.elementoActual().getNombre());
		}

	}

}
