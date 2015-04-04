package es.unileon.mtp.grupo5.puntocom.composite;

import es.unileon.mtp.grupo5.puntocom.iterator.Iterador;

/**
 * 
 * Agregado generico del patron Iterator.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 * @param <T> Tipo de objetos que agrega.
 */
public interface Agregado<T> {
	/**
	 * @return el numero objetos que contiene.
	 */
	int darNumHijos();
	
	/**
	 * Devuelve el hijo en la posicion i.
	 * @param i Posicion del hijo deseado.
	 * @return El hijo en la posicion i.
	 */
	 T darHijo(int i);
	
	/**
	 * Devuelve un iterador sobre el agregado actual.
	 * @param arg Tipo de iterador.
	 * @return El iterador creado.
	 */
	Iterador<T> darIterador(String arg);

}
