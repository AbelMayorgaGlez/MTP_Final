package es.unileon.mtp.grupo5.puntocom.command;

import java.io.IOException;
import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.decorator.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;

/**
 * 
 * Comando concreto. Cambia el nombre de un producto.
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
public class SetNombre extends Set {
	/**
	 * Constructor.
	 * @param tienda
	 * 				Tienda.
	 * @param parametros
	 * 				Array de Strings que contiene la informacion relativa al producto.
	 * 				En este caso el identificador o nombre y el nuevo nombre.
	 * @throws ParametrosIncorrectosException
	 * 				Se lanza si hay parametros incorrectos.
	 */
	public SetNombre(Tienda tienda,  String[] parametros) throws ParametrosIncorrectosException {
		super(tienda, parametros);
		String msg = "";
		if(parametros.length != 2){
			msg += "Error de sintaxis. Sintaxis correcta: SET NOMBRE producto, nombre";
			_logger.info("No se han introducido bien los parametros");
		}
		if(!msg.equals("")){
			throw new ParametrosIncorrectosException(msg);
		}
	}
	
	public void ejecutar() throws ErrorEjecucionException, IOException{	
		super.ejecutar();
		_elemento.setNombre(_parametros[1]);
		_logger.info("Se ha asignado correctamente el nombre "+_parametros[1]+" al elemento con identificador "+_parametros[0]);
		SalidaSingleton.getSalida().escribir("Se ha asignado correctamente el nombre "+_parametros[1]+" al elemento con identificador "+_parametros[0]);
	}

}
