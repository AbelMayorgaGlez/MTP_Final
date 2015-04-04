package es.unileon.mtp.grupo5.puntocom.command;

import java.io.IOException;

import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.decorator.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.strategyPrecio.*;

/**
 * 
 * Comando concreto. Da el precio de un determinado producto.
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
public class GetPrecio extends Get {
	
	private float _descuento;
	/**
	 * Constructor.
	 * @param tienda
	 * 				Tienda
	 * @param parametros
	 * 				Array de Strings que contiene la informacion relativa al producto del que obtener el precio.
	 * 				En este caso puede ser solo el identificador o el nombre, o tres campos, por este orden, uno indicando el identificador o el nombre, otro la palabra "rebaja" y otro el porcentaje a rebajar.
	 * @throws ParametrosIncorrectosException
	 * 				Se lanza cuando los parametros son incorrectos.
	 */
	public GetPrecio(Tienda tienda, String[] parametros) throws ParametrosIncorrectosException {
		super(tienda, parametros);
		String msg = "";
		if((parametros.length != 1) && (parametros.length != 3)){
			msg += "Error de sintaxis. Sintaxis correcta: GET PRECIO producto[, REBAJA, porcentaje]";
			_logger.info("No se han introducido bien los parametros");
		} else if (parametros.length == 3){
			try{
				_descuento = Float.parseFloat(parametros[2]);
			} catch (NumberFormatException e){
				msg += "El descuento debe ser un numero";
			}
		}
		if(!msg.equals("")){
			throw new ParametrosIncorrectosException(msg);
		}
	}
	
	@Override
	public void ejecutar() throws ErrorEjecucionException, IOException {
		try {
			super.ejecutar();
			/*
			 * Caso de precio con rebaja.
			 */
			if(_parametros.length == 3){
				if(_parametros[1].equalsIgnoreCase("REBAJA")){
					_elemento.setEstrategia(new Rebaja(_descuento/100.0f));
					SalidaSingleton.getSalida().escribir("El precio del producto " +_elemento.getNombre()+ " es "+String.valueOf(_elemento.getPrecio()));
					_logger.info("El precio del producto " +_elemento.getNombre()+ " es "+String.valueOf(_elemento.getPrecio()));
					_elemento.setEstrategia(new PrecioPorDefecto());
				}
			}
			
			/*
			 * Caso de precio sin rebaja.
			 */
			else{
				SalidaSingleton.getSalida().escribir("El precio del producto " + _elemento.getNombre() + " es " + String.valueOf(_elemento.getPrecio()));
				_logger.info("El precio del producto " + _elemento.getNombre() + " es " + String.valueOf(_elemento.getPrecio()));
			}
		} catch (ParametrosIncorrectosException e) {
			throw new ErrorEjecucionException(e.getMessage());
		} 

	}

}
