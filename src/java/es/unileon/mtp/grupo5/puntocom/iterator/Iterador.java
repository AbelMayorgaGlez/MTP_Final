package es.unileon.mtp.grupo5.puntocom.iterator;

/**
 * Interfaz del patron Iterator.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @param <T> Clase sobre la que se itera.
 * @version 1.0
 */
public interface Iterador<T> {

	/**
	 * Obtiene el primer elemento.
	 * @return El primer elemento.
	 */
	T primerElemento();
	
	/**
	 * Obtiene el siguiente elemento.
	 * @return El siguiente elemento. null si no hay siguiente.
	 */
	T siguienteElemento();
	
	/**
	 * Comprueba si hay siguiente elemento y avanza el iterador a el.
	 * @return true si hay siguiente, false de lo contrario.
	 */
	boolean haySiguiente();
	
	/**
	 * Devuelve el elemento actual.
	 * @return El elemento actual.
	 */
	T elementoActual();
}
