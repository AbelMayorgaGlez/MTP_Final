package es.unileon.mtp.grupo5.puntocom.strategyIterador;

import es.unileon.mtp.grupo5.puntocom.composite.Elemento;

/**
 * Estrategia concreta del patron Strategy.
 * Valida un Elemento si es un producto de precio menor o igual al limite.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class EstrategiaIteradorPrecio implements EstrategiaIteradorComposite {

	/**
	 * Precio limite.
	 */
	private float _precioLimite;
	
	/**
	 * Constructor de clase.
	 * @param precio Precio limite.
	 */
	public EstrategiaIteradorPrecio(float precio){
		_precioLimite = precio;
	}
	
	@Override
	public boolean esValido(Elemento elem) {
		return (elem.getComposite() == null) && (elem.getPrecio() <= _precioLimite);
	}

}
