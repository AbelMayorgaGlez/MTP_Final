package es.unileon.mtp.grupo5.puntocom.composite;

import es.unileon.mtp.grupo5.puntocom.Usuario;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.*;
import es.unileon.mtp.grupo5.puntocom.observer.*;

import java.util.*;

/**
 * 
 * Hoja del composite, representa a un producto de subasta.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 2.0
 */

public class ProductoSubasta extends Producto {

	/**
	 * Puja mas alta realizada por el elemento actual.
	 */
	private float _pujaMasAlta;
	/**
	 * Usuario que va ganando la subasta.
	 */
	private Usuario _usuarioGanador;
	/**
	 * Controla si esta activa la subasta
	 */
	private boolean _pujaActiva;
	/**
	 * Conjunto de observadores de la subasta. 
	 */
	private Set<Observador> _observadores;
	
	/**
	 * Constructor de clase. Asigna los identificadores de forma secuencial.
	 * @param nombre Nombre del producto.
	 * @param descr Descripcion del producto.
	 * @param precio Precio del producto.
	 * @throws ParametrosIncorrectosException Si el precio es negativo.
	 */
	public ProductoSubasta(String nombre, String descr, float precio) throws ParametrosIncorrectosException{
		this(new IdentificadorPS(),nombre,descr,precio);
	}

	/**
	 * Constructor de clase.
	 * 
	 * @param id
	 *            Identificador para el producto.
	 * @param nombre
	 *            Nombre para el producto.
	 * @param descr
	 *            Descripcion para el producto.
	 * @param precio
	 *            Precio para el producto.
	 * @throws ParametrosIncorrectosException
	 *             Si algun parametro no es valido.
	 */
	public ProductoSubasta(Identificador id, String nombre, String descr,
			float precio) throws ParametrosIncorrectosException{
		super(nombre, descr, precio);
		try{
			_id = new IdentificadorPS(id);
		} catch (BadFormatException e){
			throw new ParametrosIncorrectosException("No puedes crear un ProductoSubasta con un identificador que no es un IdentificadorPS");
		}
		_pujaActiva = false;
		_pujaMasAlta = precio;
		_observadores = new HashSet<Observador>();
		_logger.info("ProductoSubasta creado con exito.");
	}
	
	/**
	 * Constructor de clase. Crea un ProductoSubasta a partir de otro elemento.
	 * @param id Identificador para el nuevo ProductoSubasta.
	 * @param otro Elemento que convertir.
	 * @throws ParametrosIncorrectosException Si el identificador no es un IdentificadorPS
	 */
	public ProductoSubasta(Identificador id, Elemento otro) throws ParametrosIncorrectosException{
		this(id,otro.getNombre(),otro.getDescripcion(),otro.getPrecio());
	}

	@Override
	public float getPujaMasAlta() {
		return _pujaMasAlta;
	}

	@Override
	public boolean pujar(float cantidad, Usuario usr) {
		boolean exito = _pujaActiva && (_pujaMasAlta < cantidad);
		if (exito) {
			_logger.info("Puja realizada con exito");
			_pujaMasAlta = cantidad;
			_usuarioGanador = usr;
			notificar();
		} else {
			_logger.info("Puja fallida.");
		}
		return exito;
	}

	@Override
	public void comenzarSubasta() {
		_pujaActiva = true;
	}

	@Override
	public void finalizarSubasta() {
		_pujaActiva = false;
		notificar();
		_observadores.clear();
	}
	
	@Override
	public boolean esSubastaActiva(){
		return _pujaActiva;
	}
	
	@Override
	public boolean anyadirObservador(Observador nuevo){
		return _observadores.add(nuevo);
	}
	
	@Override
	public boolean eliminarObservador(Observador obs){
		return _observadores.remove(obs);
	}
	
	@Override
	public void notificar(){
		for(Observador obs : _observadores){
			obs.actualizar(_id);
		}
	}
	
	@Override
	public Usuario getGanadorSubasta(){
		return _usuarioGanador;
	}
	

}
