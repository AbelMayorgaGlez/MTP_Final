package es.unileon.mtp.grupo5.puntocom.command;

import org.apache.log4j.Logger;

import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.composite.Elemento;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.IdentificadorDefecto;

/**
 * 
 * Comando concreto, Termina la puja por un elemento..
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class EndAuction implements Comando {

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
	static Logger _logger = Logger.getLogger(EndAuction.class);
	
	/**
	 * Constructor.
	 * @param tienda
	 * 				Tienda.
	 * @param parametros
	 * 				Array de strings que contiene la informacion relativa al producto
	 * 				En este caso solo contiene el producto que hay que dejar de subastar.
	 * @throws ParametrosIncorrectosException
	 * 				Se lanza cuando hay parametros incorrectos
	 */
	public EndAuction(Tienda tienda, String[] parametros) throws ParametrosIncorrectosException{
		_parametros=parametros;
		_tienda=tienda;
		String msg="";
		if(_parametros.length!=1){
			msg += "Error de sintaxis. Sintaxis correcta: ENDAUCTION producto";
			_logger.warn("No se han introducido bien los parametros");
		}
		if(!msg.equals("")){
			throw new ParametrosIncorrectosException(msg);
		}
	}
	
	@Override
	public void ejecutar() throws ErrorEjecucionException {
		String msg="";

		Elemento elem = _tienda.buscar(new IdentificadorDefecto(_parametros[0]));
		
		if(elem == null){		
			elem = _tienda.buscarPorNombre(_parametros[0]);
		}
		
		if(elem == null){
			msg += "No hay elementos que coincidan con ese nombre";
			_logger.warn("No se encuentra ningun producto con el nombre o el identificador indicado");
		} else {	
			try{
				/*
				 * Si la subasta no esta activa no se puede desactivar, sino quita de la subasta.
				 */
				if(!elem.esSubastaActiva()){
					msg += "La subasta por el producto indicado no esta activa";
					_logger.warn("La puja no esta activa");
				} else {
					elem.finalizarSubasta();
					_logger.info("Subasta por el elemento " + elem.getNombre() + " finalizada con exito");
				}
			}
			catch(OperacionNoPermitidaException exc){
				msg += "El producto introducido no es de tipo Subasta";
				_logger.warn("El producto no es de tipo subasta");
			}
		}
		
		if(!msg.equals("")){
			throw new ErrorEjecucionException(msg);
		}		
	}
}
