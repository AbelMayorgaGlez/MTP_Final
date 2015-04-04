package es.unileon.mtp.grupo5.puntocom.command;

import java.io.IOException;
import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.decorator.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;

/**
 * 
 * Comando concreto.Da la descripcion de un determinado producto.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class GetDescripcion extends Get {
	/**
	 * Constructor.
	 * @param tienda
	 * 				Tienda
	 * @param parametros
	 * 				Array de Strings que contiene la informacion relativa al producto del que obtener la descripcion.
	 * 				En este caso solo es el identificador o el nombre del producto.
	 * @throws ParametrosIncorrectosException
	 * 				Se lanza si los parametros son incorrectos.
	 */
	public GetDescripcion(Tienda tienda, String[] parametros) throws ParametrosIncorrectosException {
		super(tienda, parametros);
		String msg = "";
		if(parametros.length!=1){
			msg += "Error de sintaxis. Sintaxis correcta: GET DESCRIPCION producto";
			_logger.info("No se han introducido bien los parametros");
		}
		if(!msg.equals("")){
			throw new ParametrosIncorrectosException(msg);
		}
	}

	@Override
	public void ejecutar() throws ErrorEjecucionException, IOException{
		super.ejecutar();
		SalidaSingleton.getSalida().escribir("La descripcion del producto: " +_elemento.getNombre() + " es:\t"+_elemento.getDescripcion());
	}

}
