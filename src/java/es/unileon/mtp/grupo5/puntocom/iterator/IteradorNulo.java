package es.unileon.mtp.grupo5.puntocom.iterator;


/**
 * Iterador concreto. Iterador nulo, nunca tiene siguiente elemento
 * y el elemento actual siempre es null.
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
public class IteradorNulo<T> implements Iterador<T> {

	@Override
	public T primerElemento() {
		return null;
	}

	@Override
	public T siguienteElemento() {
		return null;
	}

	@Override
	public boolean haySiguiente() {
		return false;
	}

	@Override
	public T elementoActual() {
		return null;
	}

}
