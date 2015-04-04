package es.unileon.mtp.grupo5.puntocom.command;

import es.unileon.mtp.grupo5.puntocom.decorator.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.IdentificadorDefecto;
import es.unileon.mtp.grupo5.puntocom.*;
import es.unileon.mtp.grupo5.puntocom.composite.*;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * 
 * Comando concreto, saca a subasta un producto de subasta.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class Auction implements Comando {
	
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
	static Logger _logger = Logger.getLogger(Auction.class);
	/**
	 * Constructor del auction.
	 * @param tienda
	 * 				Tienda donde se encuentran los productos
	 * @param parametros
	 * 				Array de Strings que contiene la informacion relativa al producto del que se quiere poner en venta.
	 * 				En este caso solo tiene un String y es el nombre o el identificador del producto de Subasta.
	 * @throws ParametrosIncorrectosException
	 * 				Salta la excepcion cuando los parametros no son correctos.
	 */
	public Auction(Tienda tienda, String[] parametros) throws ParametrosIncorrectosException{
		_parametros=parametros;
		_tienda=tienda;
		String msg="";
		if(_parametros.length!=1){
			msg += "Error de sintaxis. Sintaxis correcta: AUCTION producto";
			_logger.warn("No se han introducido bien los parametros");
		}	
		if(!msg.equals("")){
			throw new ParametrosIncorrectosException(msg);
		}
	}
	
	@Override
	public void ejecutar() throws ErrorEjecucionException, IOException {
		String msg="";
		Elemento elem;
		/*
		 * Busca el elemento.
		 */
		elem = _tienda.buscar(new IdentificadorDefecto(_parametros[0]));
		
		if(elem == null){	
			_logger.warn("Producto no encontrado por identificador, pasamos a buscar por nombre");
			elem = _tienda.buscarPorNombre(_parametros[0]);
		}		
		
		if(elem == null){
			msg += "No hay elementos que coincidan con " + _parametros[0];
			_logger.warn("No se encuentra el elemento buscando por nombre.");
		} else {
			try{	
				/*
				 * Si el elemento ya es una subasta activa no se puede poner activa de nuevo.
				 */
				if(elem.esSubastaActiva()){
					msg += "La subasta por el producto indicado ya esta activa";
					_logger.warn("La subasta por el producto ya estaba activa");
				} else {
					/*
					 * Sino comienza la subasta.
					 */
					elem.comenzarSubasta();
					_logger.info("Puja comenzada por el elemento: " + elem);
				}
			}	
			catch(OperacionNoPermitidaException exc){
				msg += "El producto introducido no es de tipo Subasta";
				_logger.warn("El producto no es de tipo subasta");
			}
		}
		
		if(!msg.equals("")){
			throw new ErrorEjecucionException(msg);
		} else {
			SalidaSingleton.getSalida().escribir(elem.getNombre() +" " + elem.getId() + " sacado a subasta con un precio de " + Float.toString(elem.getPrecio()));
		}
	}
}
