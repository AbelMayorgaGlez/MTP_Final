package es.unileon.mtp.grupo5.puntocom.command;

import org.apache.log4j.Logger;

import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.composite.Elemento;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.IdentificadorDefecto;

import java.io.*;

/**
 * 
 * Comando abstracto. Es una clase abstracta de la cual derivan todos los posibles
 * Set's que permite el programa (Descripcion, Nombre, Categoria, Precio y Tipo).
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public abstract class Set implements Comando {
	/**
	 * Tienda.
	 */
	protected Tienda _tienda;
	
	/**
	 * Elemento
	 */
	protected Elemento _elemento;
	
	/**
	 * Array de Strings.
	 */
	protected String[] _parametros;
	
	/**
	 * Log de la clase.
	 */
	static Logger _logger = Logger.getLogger(Set.class);
	/**
	 * Constructor.
	 * @param tienda
	 * 				Tienda.
	 * @param parametros
	 * 				Array de Strings que contiene la informacion relativa al producto.
	 */
	protected Set(Tienda tienda, String[] parametros){
		_tienda=tienda;
		_parametros=parametros;
	}
	@Override
	public void ejecutar() throws ErrorEjecucionException, IOException{
		String msg = "";
		_elemento = _tienda.buscar(new IdentificadorDefecto(_parametros[0]));
		
		if(_elemento == null){		
			_elemento = _tienda.buscarPorNombre(_parametros[0]);
		}
		
		if(_elemento == null){
			msg += "No hay elementos que coincidan con " + _parametros[0];
			_logger.warn("No se ha podido encontrar un elemento que coincida con el nombre o con el identificador: "+_parametros[0]);
		}
		if(!msg.equals("")){
			throw new ErrorEjecucionException(msg);
		}
	}

}
