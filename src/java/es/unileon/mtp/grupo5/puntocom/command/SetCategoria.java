package es.unileon.mtp.grupo5.puntocom.command;

import java.io.IOException;
import java.util.Arrays;
import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.composite.Elemento;
import es.unileon.mtp.grupo5.puntocom.decorator.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;

/**
 * Comando concreto. Cambia la categoria de un producto.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class SetCategoria extends Set {
	/**
	 * Constructor.
	 * @param tienda
	 * 				Tienda.
	 * @param parametros
	 * 				Array de Strings que contiene la informacion relativa al producto.
	 * 				En este caso contiene, el identificiador o el nombre del producto y su nueva localizacion.
	 * @throws ParametrosIncorrectosException
	 * 				Se lanza si hay parametros incorrectos.
	 */
	public SetCategoria(Tienda tienda, String[] parametros) throws ParametrosIncorrectosException {
		super(tienda, parametros);
		String msg = "";
		if(parametros.length<2){
			msg += "Error de sintaxis. Sintaxis correcta: SET CATEGORIA producto, categoria[, subcategoria1, subcategoria2,...]";
			_logger.info("No se han introducido bien los parametros");
		}
		if(!msg.equals("")){
			throw new ParametrosIncorrectosException(msg);
		}
	}

	@Override
	public void ejecutar() throws ErrorEjecucionException, IOException{
		super.ejecutar();
		//Orden parametros: elemento, ruta de categorias donde moverlo.
		Elemento cat = null;
		String[] categorias = Arrays.copyOfRange(_parametros, 1, _parametros.length);
		cat = _tienda.crearCategorias(categorias);
		_logger.info("Categorias creadas con exito");
		
		try {
			_tienda.anyadir(_elemento, cat.getId());
			SalidaSingleton.getSalida().escribir("Se ha anyadido el elemento " + _elemento.getNombre() + " a la categoria " + cat.getNombre());
			_logger.info("Se ha anyadido el elemento " + _elemento.getNombre() + " a la categoria " + cat.getNombre());
		} catch (ParametrosIncorrectosException e) {
			_logger.warn("No se ha podido anyadir el elemento a la categoria");
			throw new ErrorEjecucionException(e.getMessage());
		} 

	}

}
