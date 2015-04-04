package es.unileon.mtp.grupo5.puntocom.command;

import es.unileon.mtp.grupo5.puntocom.composite.*;
import java.io.IOException;
import org.apache.log4j.Logger;
import es.unileon.mtp.grupo5.puntocom.decorator.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.IdentificadorDefecto;
import es.unileon.mtp.grupo5.puntocom.Tienda;

/**
 * 
 * Comando concreto. Saca a venta un producto de venta.
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
public class Sell implements Comando{
	/**
	 * Tienda.
	 */
	private Tienda _tienda;
	
	/**
	 * Array de Strings.
	 */
	private String[] _parametros;

	
	/**
	 * Log de la clase.
	 */
	static Logger _logger = Logger.getLogger(Sell.class);
	/**
	 * Constructor.
	 * @param tienda
	 * 				Tienda.
	 * @param parametros
	 * 				Array de strings que contiene la informacion relativa al producto
	 * 				En este caso solo contiene el producto que hay que activar la venta
	 * @throws ParametrosIncorrectosException
	 * 				Se lanza si hay parametros incorrectos.
	 */
	public Sell(Tienda tienda, String[] parametros) throws ParametrosIncorrectosException{
		_tienda = tienda;
		_parametros = parametros;
		String msg="";
		
		if(_parametros.length!=1){
			msg += "Error de sintaxis. Sintaxis correcta: SELL producto";
			_logger.warn("No se han introducido correctamente los parametros");
		}		
		if(!msg.equals("")){
			throw new ParametrosIncorrectosException(msg);
		}
		
	}
	
	@Override
	public void ejecutar() throws ErrorEjecucionException, IOException{	
		Elemento _elemento = _tienda.buscar(new IdentificadorDefecto(_parametros[0]));
		if(_elemento == null){		
			_elemento = _tienda.buscarPorNombre(_parametros[0]);
		}
		String msg="";
		
		if(_elemento == null){
			msg += "No hay elementos que coincidan con ese nombre";
			_logger.warn("No se ha podido encontrar un elemento que coincida con el nombre o con el identificador: "+_parametros[0]);
		} else {
			try {
				_elemento.sacarAVenta();
				_logger.info("Elemento "+ _elemento.getNombre() + " sacado a venta");
				SalidaSingleton.getSalida().escribir(_elemento.getNombre()+ " " +_elemento.getId().toString()+ " a la venta");	
			} catch (OperacionNoPermitidaException e) {
				msg += e.getMessage();
				_logger.warn("No se ha podido sacar a venta el elemento "+_elemento);
			}
		}
		
		if(!msg.equals("")){
			throw new ErrorEjecucionException(msg);
		}
	}	
	

}
