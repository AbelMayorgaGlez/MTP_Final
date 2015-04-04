package es.unileon.mtp.grupo5.puntocom.strategyIterador;

import es.unileon.mtp.grupo5.puntocom.composite.Elemento;

/**
 * Interfaz del patron Strategy.
 * Estrategia aplicada a recorrer un Composite con un Iterator.
 * El patron Iterator es el contexto de esta estrategia.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public interface EstrategiaIteradorComposite {
	
	/**
	 * Comprueba si el Elemento valida la estrategia.
	 * @param elem Elemento a validar.
	 * @return true si es valido, false de lo contrario.
	 */
	boolean esValido(Elemento elem);
}
