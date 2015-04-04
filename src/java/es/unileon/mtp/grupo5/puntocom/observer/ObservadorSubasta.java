package es.unileon.mtp.grupo5.puntocom.observer;

import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.*;
import es.unileon.mtp.grupo5.puntocom.handler.*;

import org.apache.log4j.*;

import java.util.*;

/**
 * Observador concreto. Observa productos de subasta
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class ObservadorSubasta implements Observador {
	
	/**
	 * Conjunto de elementos observados.
	 */
	private Set<Elemento> _observados;
	/**
	 * Usuario del observador actual.
	 */
	private Usuario _user;
	
	static Logger _logger = Logger.getLogger(ObservadorSubasta.class);
	
	/**
	 * Crea un observador para el usuario pasado.
	 * @param user El usuario para el que observar.
	 * @param elem Elemento a seguir en la subasta.
	 */
	public ObservadorSubasta(Usuario user, Elemento elem){
		_user = user;
		_observados = new HashSet<Elemento>();
		seguirNuevaSubasta(elem);
	}

	/**
	 * Abandona la subasta de un elemento.
	 * @param elem elemento al que dejar de seguir.
	 * @return true si se puede abandonar, y false si se va ganando la puja, por lo que no se podria abandonar.
	 */
	public boolean abandonarSubasta(Elemento elem) {
		try {
			boolean posible = !(elem.getGanadorSubasta().equals(_user));
			if(posible){
				_observados.remove(elem);
					elem.eliminarObservador(this);
				_logger.info("Abandonando la subasta");
			} else {
				_logger.warn("Intento de abandonar la subasta cuando se va ganando");
			}
			return posible;
		} catch (OperacionNoPermitidaException e) {
			_logger.warn("El elemento no puede ser subastado");
			// Si salta esta excepcion, es porque el elemento no admite subastas.
			return false;
		}
		
	}

	@Override
	public void actualizar(Identificador id) {
	
		// Busca el elemento cambiado dentro del conjunto
		Elemento cambiado = null;
		Iterator<Elemento> it = _observados.iterator();
		while((cambiado == null) && it.hasNext()){
			cambiado=it.next().buscar(id);
		}
		try{
			if(cambiado.esSubastaActiva()){
				/*
				 * Si la puja sigue activa, se notifica al usuario que alguien le ha sobrepujado,
				 * a no ser que haya sido el usuario quien ha pujado.
				 */			
				if(!(cambiado.getGanadorSubasta().equals(_user))){
					_user.notificarSobrePuja(cambiado);
				}
			} else {
				// Si la subasta termino, se notifica al usuario que la subasta ha terminado y si ha ganado.
				_observados.remove(cambiado);
				_user.notificarResultadoSubasta(cambiado, cambiado.getGanadorSubasta().equals(_user));
			}
		} catch (OperacionNoPermitidaException e){
			_logger.warn("No se ha podido actualizar");
		}
	}

	/**
	 * Devuelve el numero de subastas que esta siguiendo el observador.
	 * @return el numero de subastas que esta siguiendo el observador.
	 */
	public int getCantidadSubastas() {
		return _observados.size();
	}

	/**
	 * Puja por un elemento determinado a peticion de su usuario.
	 * @param elem El elemento por el que pujar.
	 * @param precio El precio por el que pujar.
	 * @return true si la puja tuvo exito y se va en cabeza.
	 */
	public boolean pujar(Elemento elem, float precio) {
		boolean exito = true;
		// Si el elemento no esta siendo observado, comienza su seguimiento.
		if(!_observados.contains(elem)){
			exito = seguirNuevaSubasta(elem);
		}
		// Si se sigue la subasta
		if(exito){
			try {
				exito = elem.pujar(precio,_user);
			} catch (OperacionNoPermitidaException e) {
				_logger.warn("no se puede pujar por un elemento que no sea un producto subasta");
			}
		}
		_logger.info("Puja realizada " + (exito? "con" : "sin") + "exito");
		return exito;
	}

	/**
	 * Comienza a seguir una nueva subasta, siempre y cuando no se este siguiendo ya.
	 * @param elem Elemento al que seguir.
	 * @return true si el seguimiento comienza, false si ya se estaba observando a ese elemento.
	 */
	public boolean seguirNuevaSubasta(Elemento elem) {
		boolean exito = false;
		try{
			exito = elem.anyadirObservador(this);
			if(exito){
				_observados.add(elem);
			}
			_logger.info("Siguiendo un nuevo producto");

		}catch (OperacionNoPermitidaException e){
			_logger.warn("no se puede anyadir el observador");
		}
		return exito;
	}

}
