package es.unileon.mtp.grupo5.puntocom.command;

import java.io.IOException;

import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.decorator.SalidaSingleton;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;

/**
 * 
 * Comando concreto, obtiene el tipo de un producto.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class GetTipo extends Get {
	
	/**
	 * Constructor.
	 * @param tienda
	 * 				Tienda
	 * @param parametros
	 * 				Array de Strings que contiene la informacion relativa al producto del que obtener el tipo.
	 * 				En este caso puede ser solo el identificador o el nombre, o tres campos, por este orden, uno indicando el identificador o el nombre, otro la palabra "rebaja" y otro el porcentaje a rebajar.
	 * @throws ParametrosIncorrectosException
	 * 				Se lanza cuando los parametros son incorrectos.
	 */
	public GetTipo(Tienda tienda, String[] parametros) throws ParametrosIncorrectosException{
		super(tienda, parametros);
		String msg ="";
		if(parametros.length !=1 ){
			msg += "Error de sintaxis. Sintaxis correcta: GET TIPO producto";
			_logger.info("No se han introducido bien los parametros");
		}
		if(!msg.equals("")){
			throw new ParametrosIncorrectosException(msg);
		}
	}

	@Override
	public void ejecutar() throws ErrorEjecucionException, IOException {
		super.ejecutar();
		String tipo = _elemento.getClass().getName();
		if(tipo.endsWith("ProductoVenta")){
			tipo = "Producto de venta";
		} else if(tipo.endsWith("ProductoSubasta")){
			tipo = "Producto de subasta";
		} else {
			tipo = "Tipo de producto no identificado";
		}
		SalidaSingleton.getSalida().escribir("Tipo de producto: " + tipo);
	}

}
