package es.unileon.mtp.grupo5.puntocom.command;

import org.apache.log4j.Logger;
import es.unileon.mtp.grupo5.puntocom.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.handler.*;
import java.io.*;

/**
 * 
 * Comando abstracto. Es una clase abstracta de la cual derivan todos los posibles
 * Get's que permite el programa (Descripcion, Nombre, Categoria, Precio y Tipo).
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public abstract class Get implements Comando {

	/**
	 * Tienda.
	 */
	protected Tienda _tienda;
	
	/**
	 * Array de Strings.
	 */
	protected String[] _parametros;
	
	/**
	 * Elemento sobre el que iniciar la puja.
	 */
	protected Elemento _elemento;
	
	/**
	 * Log de la clase.
	 */
	static Logger _logger = Logger.getLogger(EndAuction.class);

	/**
	 * Constructor.
	 * @param tienda
	 * 				Tienda.
	 * @param parametros
	 * 				Array de Strings que contiene la informacion referente al comando.
	 */
	protected Get(Tienda tienda, String[] parametros){
		_tienda = tienda;
		_parametros = parametros;
	}
	

	@Override
	public void ejecutar() throws ErrorEjecucionException, IOException{
		_elemento = _tienda.buscar(new IdentificadorDefecto(_parametros[0]));
		if(_elemento == null){		
			_elemento = _tienda.buscarPorNombre(_parametros[0]);
		}
		String msg="";
		
		
		if(_elemento == null){
			msg += "No hay elementos que coincidan con ese nombre";
			_logger.warn("No se ha encontrado ningun elemento con el nombre o identificador "+_parametros[0]);
		}
		if(!msg.equals("")){
			throw new ErrorEjecucionException(msg);
		}
	}

}
