package es.unileon.mtp.grupo5.puntocom.strategyPrecio;

/**
 * 
 * Interfaz de las estrategias para el calculo de precio.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public interface EstrategiaPrecio {
	
	/**
	 * Aplica un algoritmo determinado sobre el precio pasado.
	 * @param precio Precio sobre el que aplicar la estrategia.
	 * @return El precio calculado.
	 */
	public float calcularPrecio(float precio);
}
