package es.unileon.mtp.grupo5.puntocom.command;

import java.io.IOException;

import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.decorator.SalidaSingleton;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.*;

/**
 * 
 * Comando concreto, establece el tipo de un producto.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class SetTipo extends Set {
	
	/**
	 * Constructor.
	 * @param tienda
	 * 				Tienda.
	 * @param parametros
	 * 				Array de Strings que contiene la informacion relativa al producto.
	 * 				En este caso el identificador o nombre y el nuevo tipo.
	 * @throws ParametrosIncorrectosException
	 * 				Se lanza si hay parametros incorrectos.
	 */
	public SetTipo(Tienda tienda, String[] parametros) throws ParametrosIncorrectosException{
		super(tienda, parametros);
		String msg = "";
		if(parametros.length != 2){
			msg += "Error de sintaxis. Sintaxis correcta: SET TIPO producto, tipo";
			_logger.warn("No se han introducido bien los parametros");
		}
		if(!msg.equals("")){
			throw new ParametrosIncorrectosException(msg);
		}
	}
	
	public void ejecutar() throws ErrorEjecucionException, IOException{
		super.ejecutar();
		Elemento nuevo = null;
		try{
			/*
			 * Comprueba el tipo deseado y crea el producto consecuentemente
			 */
			if(_parametros[1].equalsIgnoreCase("venta")){
				nuevo = new ProductoVenta(new IdentificadorPV(),_elemento);
			} else if(_parametros[1].equalsIgnoreCase("subasta")){
				nuevo = new ProductoSubasta(new IdentificadorPS(),_elemento);
			}
		} catch (ParametrosIncorrectosException e){
			_logger.warn("Error convirtiendo el producto");
		}
		/*
		 * Si nuevo sigue siendo null, es porque el tipo no existia, si no
		 * se borra el viejo del arbol y se anyade el nuevo.
		 */
		if(nuevo != null){
			_elemento.getPadre().anyadir(nuevo);
			_elemento.getPadre().eliminar(_elemento.getId());
			SalidaSingleton.getSalida().escribir("Producto convertido, ahora es de " + _parametros[1]);
			_logger.info("Producto convertido ahora es de " + _parametros[1]);
		} else {
			throw new ErrorEjecucionException("No se ha podido convertir el producto");
		}
	}

}
