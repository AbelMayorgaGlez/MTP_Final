package es.unileon.mtp.grupo5.puntocom.observer;

import es.unileon.mtp.grupo5.puntocom.handler.*;

/**
 * Interfaz Observer del patron Observer.
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public interface Observador {
	
	/**
	 * Actualiza el estado del observador.
	 * @param id Identificador del objeto que llama a este metodo.
	 */
	void actualizar(Identificador id);

}
