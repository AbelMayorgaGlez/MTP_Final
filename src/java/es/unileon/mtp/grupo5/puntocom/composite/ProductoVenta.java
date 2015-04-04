package es.unileon.mtp.grupo5.puntocom.composite;

import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.*;

/**
 * 
 * Hoja del composite. Representa a los productos de venta inmediata.
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class ProductoVenta extends Producto {
	
	private boolean _ventaActiva;

	/**
	 * Constructor de clase. Asigna los identificadores de forma secuencial.
	 * @param nombre Nombre del producto.
	 * @param descr Descripcion del producto.
	 * @param precio Precio del producto.
	 * @throws ParametrosIncorrectosException Si el precio es negativo.
	 */
	public ProductoVenta(String nombre, String descr, float precio) throws ParametrosIncorrectosException{
		this(new IdentificadorPV(),nombre,descr,precio);
	}
	
	/**
	 * Constructor de clase.
	 * @param id Identificador para el producto.
	 * @param nombre Nombre para el producto.
	 * @param descr Descripcion para el producto.
	 * @param precio Precio para el producto.
	 * @throws ParametrosIncorrectosException Si algun parametro no es valido.
	 */
	public ProductoVenta(Identificador id, String nombre, String descr, float precio) throws ParametrosIncorrectosException{
		super(nombre, descr, precio);
		try{
			_id = new IdentificadorPV(id);
		} catch (BadFormatException e){
			throw new ParametrosIncorrectosException("No se puede crear un ProductoVenta con un identificador que no es un IdentificadorPV");
		}
		_ventaActiva = false;
		_logger.info("ProductoVenta creado con exito");
	}
	
	/**
	 * Constructor de clase a partir de otro Elemento.
	 * @param id Identificador para el producto.
	 * @param otro Elemento a convertir.
	 * @throws ParametrosIncorrectosException Si id no es un IdentificadorPV.
	 */
	public ProductoVenta(Identificador id, Elemento otro) throws ParametrosIncorrectosException{
		this(id, otro.getNombre(), otro.getDescripcion(), otro.getPrecio());
	}
	
	@Override
	public void sacarAVenta(){
		_ventaActiva = true;
	}
	
	@Override
	public void terminarVenta(){
		_ventaActiva = false;
	}
	
	@Override
	public boolean esVentaActiva(){
		return _ventaActiva;
	}
}
