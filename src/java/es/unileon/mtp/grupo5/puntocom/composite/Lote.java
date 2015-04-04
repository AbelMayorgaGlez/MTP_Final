package es.unileon.mtp.grupo5.puntocom.composite;

import java.util.*;

import es.unileon.mtp.grupo5.puntocom.handler.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;


/**
 * 
 * Categoria especial que solo puede incluir productos de venta.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
*/

public class Lote extends Categoria{
	
	public Lote(String nombre, String descripcion) throws ParametrosIncorrectosException{
		this(new IdentificadorLote(),nombre,descripcion);
	}
	/**
	 * Constructor de clase.
	 * @param id Identificador para el lote.
	 * @param nombre Nombre para el lote.
	 * @param descripcion Descripcion para el lote.
	 * @throws ParametrosIncorrectosException Si id no es un IdentificadorLote.
	 */
	public Lote(Identificador id, String nombre, String descripcion) throws ParametrosIncorrectosException{
		_nombre = nombre;
		_descripcion = descripcion;
		_elementos = new ArrayList<Elemento>();
		try{
			_id = new IdentificadorLote(id);
		} catch (BadFormatException e){
			_logger.warn("Se ha intentado crear un Lote con un identificador que no es IdentificadorLote.");
			throw new ParametrosIncorrectosException("El identificador debe ser un identificador de Lote.\n");
		}
		_logger.info("Lote creado con exito");
	}
	
	/**
	 * Obtiene la lista de productos del elemento pasado y los anyade al lote si se puede.
	 * @param elem Elemento del que anyadir sus productos al lote.
	 */
	@Override
	public boolean anyadir(Elemento elem){
		boolean exito = false;
		List<Elemento> lista = elem.getProductos();
		//Para cada elemento de la lista, si es valido, lo  quito de donde esta y lo anyado al lote.
		for(Elemento e : lista){
			if(esValido(e)){
				if(e.getPadre() != null){
					e.getPadre().eliminar(e.getId());
				}
				_elementos.add(e);
				e.setPadre(this);
				exito |= true; // exito = exito || true;
				_logger.info("Se ha anyadido correctamente");
			}
		}
		return exito;		
	}
	
	/**
	 * Comprueba si el lote esta a la venta.
	 * @return true si todos los productos que incluye estan a la venta.
 	 */
	@Override
	public boolean esVentaActiva(){
		boolean activa = true;
		Iterator<Elemento> it = _elementos.iterator();
		while(activa && it.hasNext()){
			try{
				activa &= it.next().esVentaActiva();
			} catch (OperacionNoPermitidaException e){
				_logger.warn("Se ha intentado saber si un producto subasta esta en venta");
			}
		}
		return activa;
	}
		
	@Override
	public String toString(){
		String msg = "Lote: " + _nombre + ". " + _descripcion +":\n";
		for(Elemento e : _elementos){
			msg += "\t" + e.toString();
		}
		return msg;
	}
	
	/**
	 * Comprueba si el elemento pasado se puede incluir en el lote.
	 * @param elem Elemento que comprobar.
	 * @return True si elem es un ProductoVenta, false de lo contrario.
	 */
	private boolean esValido(Elemento elem){
		boolean valido = false;
		try{
			/*
			 * Si el elemento se puede convertir a un ProductoVenta con su propio identificador,
			 * es porque es un ProductoVenta.
			 */		
			new ProductoVenta(elem.getId(), elem);
			valido = true;
		} catch (ParametrosIncorrectosException e){
			_logger.warn("Se han introducido mal los parametros");
		}
		return valido;
	}
	
}
