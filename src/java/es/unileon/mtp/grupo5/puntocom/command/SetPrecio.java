package es.unileon.mtp.grupo5.puntocom.command;

import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.decorator.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;

import java.io.*;

/**
 * 
 * Comando concreto. Cambia el precio de un producto.
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
public class SetPrecio extends Set {

	private float _precio;
	/**
	 * Constructor.
	 * @param tienda
	 * 				Tienda.
	 * @param parametros
	 * 				Array de Strings que contiene la informacion relativa al producto.
	 * 				En este caso el identificador o nombre y el nuevo precio.
	 * @throws ParametrosIncorrectosException
	 * 				Se lanza si hay parametros incorrectos.
	 */
	public SetPrecio(Tienda tienda, String[] parametros) throws ParametrosIncorrectosException {
		super(tienda, parametros);
		String msg = "";
		if(parametros.length != 2){
			msg += "Error de sintaxis. Sintaxis correcta: SET PRECIO producto, precio";
		} else {
			try{
				_precio = Float.parseFloat(_parametros[1]);
			} catch (NumberFormatException e){
				msg += "El precio debe de ser numerico";
			}
		}
		if(!msg.equals("")){
			_logger.warn("No se han introducido bien los parametros");
			throw new ParametrosIncorrectosException(msg);
		}
	}

	@Override
	public void ejecutar() throws ErrorEjecucionException, IOException{
		super.ejecutar();
		try{
			_elemento.setPrecio(_precio);
			_logger.info("Se ha asignado correctamente un precio de "+Float.toString(_elemento.getPrecio())+" al elemento "+_elemento.getNombre());
			SalidaSingleton.getSalida().escribir("Se ha asignado correctamente un precio de "+Float.toString(_elemento.getPrecio())+" al elemento "+_elemento.getNombre());
		} catch (ParametrosIncorrectosException e){
			throw new ErrorEjecucionException(e.getMessage());
		} catch (OperacionNoPermitidaException e){
			throw new ErrorEjecucionException(e.getMessage());
		}
	}
}
