package es.unileon.mtp.grupo5.puntocom;

import java.io.IOException;
import java.util.*;
import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.decorator.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.observer.*;
import org.apache.log4j.*;

/**
 * Clase que representa un usuario.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class Usuario {
	/**
	 * Conjunto de elemenos ganados.
	 */
	private Set<Elemento> _ganados;
	
	/**
	 * Conjunto de elementos que han sido sobrepujados.
	 */
	private Set<Elemento> _sobrePujados;
	
	/**
	 * Observador del usuario.
	 */
	private ObservadorSubasta _observador;
	
	/**
	 * Nombre del usuario.
	 */
	private String _nombre;
	
	/**
	 * logger de la clase.
	 */
	private Logger _logger = Logger.getLogger(Usuario.class);
	
	/**
	 * Constructor de clase.
	 * @param nombre Nombre del usuario.
	 */
	public Usuario(String nombre){
		_nombre = nombre;
		_ganados = new HashSet<Elemento>();
		_sobrePujados = new HashSet<Elemento>();
		_logger.info("Usuario creado con exito");
	}
	/**
	 * Metodo que puja por un elemento.
	 * @param elem
	 * 			Elemento por el que pujar
	 * @param precio
	 * 			Precio de la puja.
	 */
	public void pujar(Elemento elem, float precio){
		try {
			if(darObservadorSubasta(elem).pujar(elem, precio)){
					SalidaSingleton.getSalida().escribir(_nombre + " ha pujado por el producto "+ elem.getNombre() + " con un precio de " + Float.toString(precio));
				_logger.info(_nombre + " ha pujado por el producto " + elem.getNombre() + " con un precio de " + Float.toString(precio));
				// Si la puja ha tenido exito, entonces sacamos al elemento de la lista de sobrepujados	
				_sobrePujados.remove(elem);
			} else {
				SalidaSingleton.getSalida().escribir("El producto de subasta aun no se ha sacado a subasta");
			}
		} catch (IOException e) {
			_logger.error("No se ha podido escribir. El programa finalizara");
			System.exit(0);
		}
	}
	
	  
	/**
	 * Notifica el resultado de la subasta al usuario, y si ya no sigue mas subastas, borra al observador. 
	 * @param elem
	 * 			Elemento sobre el que se ha pujado.
	 * @param ganada
	 * 			Booleano que comprueba si ha ganado el usuario la puja o no.
	 */
	public void notificarResultadoSubasta(Elemento elem, boolean ganada){
		//Si no quedan mas subastas, borra su observador.
		if(_observador.getCantidadSubastas() == 0){
			_observador = null;
			_logger.info("Borrado el observador de " + _nombre);
		}
		// Si se ha ganado la subasta, entonces anyadimos el elemento a la lista de productos ganados.
		if(ganada){
			_ganados.add(elem);
			_logger.info(_nombre + " ha ganado la subasta");
			try {
				SalidaSingleton.getSalida().escribir(_nombre + " ha ganado la subasta por el producto "+ elem.getNombre() + " enhorabuena.");
			} catch (IOException e) {
				_logger.error("No se ha podido escribir. El programa finalizara");
				System.exit(0);
			}
		}
	}
	
	/**
	 * Anyade a la lista de sobre pujados el elemento sobre el que han pujado otros usuarios.
	 * @param elem
	 * 			Elemento sobre el que se ha sobrepujado.
	 */
	public void notificarSobrePuja(Elemento elem){
		_logger.info("A " + _nombre + " le han sobrepujado");
		try {
			SalidaSingleton.getSalida().escribir("A " + _nombre + " le han sobrepujado. El nuevo precio para " +elem.getNombre()+ " es: " + Float.toString(elem.getPujaMasAlta()));
		} catch (IOException e) {
			_logger.error("No se ha podido escribir. El programa terminara");
			System.exit(0);
		} catch (OperacionNoPermitidaException e) {
			_logger.warn("No se ha podido obtener el precio");
		}
		_sobrePujados.add(elem);
	}
	
	/**
	 * Apunta al observador perteneciente al usuario a la subasta de un elemento.
	 * @param elem
	 * 			Elemento sobre el que seguir la subasta.
	 */
	public void seguirNuevaSubasta(Elemento elem){
		darObservadorSubasta(elem).seguirNuevaSubasta(elem);
	}
	
	/**
	 * Abandona la subasta de un elemento.
	 * @param elem
	 * 			Elemento del que desapuntarse de la subasta.
	 * @return true si se puede abandonar, es decir, si no se va ganando, false de lo contrario.
	 *
	 */
	public boolean abandonarSubasta(Elemento elem){
		return darObservadorSubasta(elem).abandonarSubasta(elem);
	}
	
	/**
	 * Da un nombre al usuario.
	 * @param nombre
	 * 				Nombre.
	 */
	public void setNombre(String nombre){
		_nombre = nombre;
	}
	/**
	 * Da el nombre del usuario.
	 * @return
	 * 		Nombre.
	 */
	public String getNombre(){
		return _nombre;
	}
	
	/**
	 * 
	 * @return Da el conjunto de elementos ganados.
	 */
	public Set<Elemento> getElementosGanados(){
		return _ganados;
	}
	
	/**
	 * 
	 * @return Da el conjunto de elementos sobrepujados.
	 */
	public Set<Elemento> getElementosSobrePujados(){
		return _sobrePujados;
	}
	
	/**
	 * Da el observador del usuario. Si esta creado lo devuelve.
	 * Si no esta creado lo crea y hace que observe al elemento indicado.
	 * @param elem
	 * 			Elemento al que observar.
	 * @return
	 * 			Observador del usuario.
	 */
	private ObservadorSubasta darObservadorSubasta(Elemento elem){
		if(_observador == null){
			_observador = new ObservadorSubasta(this,elem);
			_logger.info("Creando nuevo observador para " + _nombre);
		}
		return _observador;
	}
	
	/**
	 * Devuelve true si el usuario tiene un observador.
	 * @return
	 */
	public boolean hayObservador(){
		return !(_observador == null);
	}
	
	/**
	 * Compara dos usuarios para ver si son iguales.
	 * @param otro
	 * 			Usuario sobre el que se compara.
	 * @return true en caso de que sean el mismo usuario, false de lo contrario.
	 */
	public boolean equals(Usuario otro){
		return _nombre.equals(otro.getNombre());
	}
	
}
