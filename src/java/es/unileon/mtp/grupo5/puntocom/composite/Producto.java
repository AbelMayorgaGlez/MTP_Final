package es.unileon.mtp.grupo5.puntocom.composite;

import es.unileon.mtp.grupo5.puntocom.handler.*;
import es.unileon.mtp.grupo5.puntocom.iterator.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.strategyIterador.EstrategiaIteradorComposite;
import es.unileon.mtp.grupo5.puntocom.strategyPrecio.*;

import java.util.*;

import org.apache.log4j.Logger;
/**
 * 
 * Hoja del composite, Clase abstracta padre de todos los productos.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 3.0
 */
public abstract class Producto extends Elemento {

	/**
	 * Descripcion.
	 */
	protected String _descripcion;
	
	/**
	 * Identificador.
	 */
	protected Identificador _id;
	
	/**
	 * Nombre.
	 */
	protected String _nombre;
	
	/**
	 * Precio.
	 */
	protected float _precio;
	
	/**
	 * Elemento padre del actual.
	 */
	protected Categoria _padre;
	
	/**
	 * Logger para la clase.
	 */
	static Logger _logger = Logger.getLogger(Producto.class);
	
	/**
	 * Estrategia para el producto.
	 */
	protected EstrategiaPrecio _estrategia;
	/**
	 * Constructor de clase. La asignacion del identificador se deja
	 * a los productos concretos.
	 * @param nombre Nombre para el producto.
	 * @param descr Descripcion para el producto.
	 * @param precio Precio para el producto.
	 * @throws ParametrosIncorrectosException Si alguno de los parametros no es valido.
	 */
	protected Producto(String nombre, String descr, float precio) throws ParametrosIncorrectosException{
		String msg = "";	
		_nombre = nombre;
		_descripcion = descr;
		_estrategia = new PrecioPorDefecto();
		try{
			setPrecio(precio);
		}catch (ParametrosIncorrectosException e){
			msg += e.getMessage();
		}
		
		if(!msg.equals("")){
			throw new ParametrosIncorrectosException(msg);
		}
	}
	
	@Override
	public Elemento buscar(Identificador id){
		if (_id.compareTo(id) == 0){
			_logger.info("Elemento encontrado: " + _nombre);
			return this;
		} else {
			return null;
		}
	}

	@Override
	public List<Elemento> buscar(String busqueda){
		List<Elemento> lista = new ArrayList<Elemento>();
		/* Poniendo el else evitamos que se anyada 2 veces en caso de que busqueda
		 * pertenezca a nombre y descripcion a la vez.
		 */
		if(_nombre.toLowerCase().indexOf(busqueda.toLowerCase()) != -1){
			lista.add(this);
		} else if(_descripcion.toLowerCase().indexOf(busqueda.toLowerCase()) != -1){
			lista.add(this);
		}
		
		if(!lista.isEmpty()){
			_logger.info("Se ha encontrado una coincidencia para " + busqueda + " en el elemento " + _nombre);
		}
		return lista;
	}

	/**
	 * Devuelve la cantidad de productos que contiene, es decir, 1, el mismo.
	 * @return 1;
	 */
	@Override
	public int getCantidad(){
		return 1;
	}

	@Override
	public String getDescripcion(){
		return _descripcion;
	}
	
	@Override
	public Identificador getId(){
		return _id;
	}
	
	@Override
	public String getNombre(){
		return _nombre;
	}

	@Override
	public Categoria getPadre(){
		return _padre;
	}

	@Override
	public float getPrecio(){
		return _estrategia.calcularPrecio(_precio);
	}
	
	/**
	 * Devuelve la lista de productos que contiene, es decir, una lista con el elemento actual.
	 * @return Lista con el elemento actual.
	 */
	@Override
	public List<Elemento> getProductos(){
		ArrayList<Elemento> lista = new ArrayList<Elemento>();
		lista.add(this);
		return lista;
	}
	
	@Override
	public void setDescripcion(String descripcion){
		_descripcion = descripcion;
	}
	
	@Override
	public void setEstrategia(EstrategiaPrecio nueva){
		_estrategia = nueva;
		_logger.info("Se ha asignado la estrategia correctamente");
	}

	@Override
	public void setNombre(String nombre){
		_nombre = nombre;
	}
	
	@Override
	public void setPrecio(float precio) throws ParametrosIncorrectosException{
		if (precio < 0.0f){
			_logger.warn("Se ha intentado asignar un precio negativo al producto " + _nombre);
			throw new ParametrosIncorrectosException("El precio debe ser positivo.");
		} else {
			_precio = precio;
		}
	}
	
	@Override
	protected void setPadre(Categoria padre){
		_padre = padre;
	}
	
	@Override
	public String toString(){
		return _nombre + ": " + _descripcion + ", " + Float.toString(getPrecio()) + ".\n";
	}
	
	@Override
	public Iterador<Elemento> darIterador(String arg){
		return new IteradorNulo<Elemento>();
	}
	
	@Override
	public Iterador<Elemento> darIterador(EstrategiaIteradorComposite est){
		return new IteradorNulo<Elemento>();
	}

	@Override
	public int darNumHijos(){
		return 0;
	}
	
	@Override
	public Elemento darHijo(int i){
		return null;
	}
}
