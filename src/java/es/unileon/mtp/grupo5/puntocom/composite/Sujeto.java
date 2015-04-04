package es.unileon.mtp.grupo5.puntocom.composite;

import es.unileon.mtp.grupo5.puntocom.excepciones.OperacionNoPermitidaException;
import es.unileon.mtp.grupo5.puntocom.observer.Observador;

/**
 * Interfaz Subject del patron Observer.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public interface Sujeto {

	
	/**
	 * Anyade un observador al sujeto actual.
	 * @param nuevo Observador a anyadir.
	 * @return true si el observador se anyade con exito, false de lo contrario.
	 * @throws OperacionNoPermitidaException Si el elemento actual no admite observadores.
	 */
	boolean anyadirObservador(Observador nuevo) throws OperacionNoPermitidaException;

	/**
	 * Elimina un observador del sujeto actual.
	 * @param obs Observador a eliminar.
	 * @return true si se elimina el observador con exito, false de lo contrario.
	 * @throws OperacionNoPermitidaException si el elemento actual no admite observadores.
	 */
	boolean eliminarObservador(Observador obs) throws OperacionNoPermitidaException;
	
	/**
	 * Notifica cambios a los observadores.
	 * @throws OperacionNoPermitidaException Si el elemento actual no permite ser observado.
	 */
	void notificar() throws OperacionNoPermitidaException;

}
