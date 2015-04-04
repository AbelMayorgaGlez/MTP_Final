package es.unileon.mtp.grupo5.puntocom.strategyPrecio;


import es.unileon.mtp.grupo5.puntocom.excepciones.*;

/**
 * 
 * Estrategia concreta que aplica un descuento sobre el precio.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class Rebaja implements EstrategiaPrecio {
	
	/**
	 * Factor por el que se multiplica el precio (0<_rebaja<1)
	 */
	private float _rebaja;

	/**
	 * Constructor de clase.
	 * @param rebaja Rebaja a aplicar en decimal (para 10% poner 0,1).
	 * @throws ParametrosIncorrectosException Si rebaja no se encuentra en el intervalo (0,1).
	 */
	public Rebaja(float rebaja) throws ParametrosIncorrectosException{
		if((rebaja > 0) && (rebaja < 1)){
			_rebaja = 1-rebaja;
		} else {
			throw new ParametrosIncorrectosException("El descuento tiene que ser mayor que el 0%, es decir, 0, y menor que el 100%, es decir, 1.");
		}
	}
	
	/**
	 * Aplica el descuento.
	 * @param precio Precio sobre el que aplicar el descuento.
	 * @return Precio despues de aplicar el descuento.
	 */
	@Override
	public float calcularPrecio(float precio){
		return _rebaja*precio;
	}

}
