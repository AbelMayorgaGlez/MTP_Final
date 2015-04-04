package es.unileon.mtp.grupo5.puntocom.command;

import java.io.IOException;
import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.decorator.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;

/**
 * 
 * Comando concreto. Da el nombre de un determinado elemento.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class GetNombre extends Get {
	/**
	 * Constructor
	 * @param tienda
	 * 				Tienda
	 * @param parametros
	 * 				Array de Strings que contiene la informacion relativa al producto del que obtener el nombre.
	 *				En este caso solo el nombre o identificador del elemento.
	 * @throws ParametrosIncorrectosException
	 * 				Se lanza si hay parametros incorrectos.
	 */
	public GetNombre(Tienda tienda, String[] parametros) throws ParametrosIncorrectosException {
		super(tienda, parametros);
		String msg = "";
		if(parametros.length!=1){
			msg += "Error de sintaxis. Sintaxis correcta: GET NOMBRE producto";
			_logger.info("No se han introducido bien los parametros");
		}
		if(!msg.equals("")){
			throw new ParametrosIncorrectosException(msg);
		}
	}
	@Override
	public void ejecutar() throws ErrorEjecucionException, IOException{
		super.ejecutar();
		SalidaSingleton.getSalida().escribir("El nombre del producto con el identificador " +_parametros[0]+ " es: " +_elemento.getNombre());
	}

}
