package es.unileon.mtp.grupo5.puntocom.command;

import java.io.IOException;
import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.decorator.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;

/**
 * Comando concreto. Cambia la descripcion de un producto.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class SetDescripcion extends Set {
	/**
	 * Constructor.
	 * @param tienda
	 * 				Tienda.
	 * @param parametros
	 * 				Array de Strings que contiene la informacion de un producto.
	 * 				En este caso son, el identificador o nombre y la descripcion nueva.
	 * @throws ParametrosIncorrectosException
	 * 				Se lanza cuando hay parametros incorrectos.
	 */
	public SetDescripcion(Tienda tienda, String[] parametros) throws ParametrosIncorrectosException {
		super(tienda, parametros);
		String msg = "";
		if(parametros.length != 2){
			msg += "Error de sintaxis. Sintaxis correcta: SET DESCRIPCION producto, descripcion";
			_logger.info("No se han introducido bien los parametros");		
		}
		if(!msg.equals("")){
			throw new ParametrosIncorrectosException(msg);
		}
		
	}
	@Override
	public void ejecutar() throws ErrorEjecucionException, IOException{
		super.ejecutar();
		_elemento.setDescripcion(_parametros[1]);
		_logger.info("Se ha asignado correctamente la descripcion " + _parametros[1] + " al elemento " + _elemento.getNombre());
		SalidaSingleton.getSalida().escribir("Se ha asignado correctamente la descripcion " + _parametros[1] + " al elemento " + _elemento.getNombre());
	}

}
