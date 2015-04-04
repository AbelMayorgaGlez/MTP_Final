package es.unileon.mtp.grupo5.puntocom.strategyPrecio;

/**
 * 
 * Estrategia concreta que devuelve el precio por defecto, sin tocar.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class PrecioPorDefecto implements EstrategiaPrecio {

	/**
	 * Devuelve el precio tal cual.
	 * @param precio
	 * @return precio.
	 */
	@Override
	public float calcularPrecio(float precio) {
		return precio;
	}

}
