package es.unileon.mtp.grupo5.puntocom;

import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.*;
import es.unileon.mtp.grupo5.puntocom.iterator.*;
import es.unileon.mtp.grupo5.puntocom.strategyPrecio.*;
import java.util.*;
import org.apache.log4j.Logger;


/**
 * 
 * Clase que representa a una Tienda.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 2.0
 */

public class Tienda {
	/**
	 * logger de la clase.
	 */
	private Logger _logger = Logger.getLogger(Tienda.class);
	
	/**
	 * Elementos de la tienda.
	 */
	private Elemento _productos;

	/**
	 * Conjunto de usuarios.
	 */
	private Set<Usuario> _usuarios;

	/**
	 * Constructor de clase. Crea un almacen vacio para la tienda.
	 */
	public Tienda(){
		try{
			_productos = new Categoria("Almacen", "Almacen de productos");
			_usuarios = new HashSet<Usuario>();
		} catch (ParametrosIncorrectosException e){
			_logger.error("No se ha podido crear la Categoria Raiz.");
		}
	}

	/**
	 * Constructor de clase.
	 * 
	 * @param productos
	 *            Productos que contendra la tienda.
	 */
	public Tienda(Elemento productos) {
		_productos = productos;
		_usuarios = new HashSet<Usuario>();
	}

	/**
	 * Anyade un elemento a una categoria
	 * 
	 * @param anyadido
	 *            Elemento que se anyade.
	 * @param id
	 *            Identificador de la categoria en la que se anyade el elemento.
	 *            Si es null, entonces el elemento se anyade en la raiz de la tienda,
	 *            en la categoria Almacen.
	 * @return true si se ha podido anyadir, false de lo contrario.
	 * @throws ParametrosIncorrectosException
	 *             si el segundo parametro no es una categoria.
	 */
	public boolean anyadir(Elemento anyadido, Identificador id)
			throws ParametrosIncorrectosException {
		/* 
		 * Si la categoria en la que se intenta anyadir el elemento
		 * es null, entonces lo anyadimos en el nodo raiz.
		 */
		if(id == null){
			id = _productos.getId();
		}
		try {
			return buscar(id).getComposite().anyadir(anyadido);
		} catch (NullPointerException e) {
			throw new ParametrosIncorrectosException(
					"El segundo parametro debe ser una categoria.\n");
		}
	}
	/**
	 * Anyade un usuario nuevo a la tienda.
	 * @param nuevo Usuario a anyadir.
	 * @return true si se puede anyadir, false de lo contrario, es decir, si ya esta o si es null.
	 */
	public boolean anyadirUsuario(Usuario nuevo) {
		return _usuarios.add(nuevo);
	}
	
	/**
	 * Devuelve true si un usuario esta ya creado.
	 * @param nombre Nombre del usuario a buscar.
	 * @return true o false dependiendo de si esta o no.		
	 */
	public boolean contieneUsuario(String nombre){
		return (buscarUsuario(nombre) != null);
	}
	
	/**
	 * Busca a un usuario de la tienda por su nombre y lo devuelve.
	 * @param nombre Nombre del usuario.
	 * @return El usuario encontrado.
	 */
	public Usuario buscarUsuario(String nombre){
		Iterator<Usuario> itr = _usuarios.iterator();
		Usuario usr = null;
		boolean encontrado = false;
		while((!encontrado) && itr.hasNext()){
			usr = itr.next();
			encontrado = usr.getNombre().equalsIgnoreCase(nombre);
			if(!encontrado){
				usr=null;
			}
		}
		return usr;
	}
	
	/**
	 * Busca un elemento por su identificador.
	 * @param id Identificador a buscar.
	 * @return el elemento con el identificador pasado o null si no se encuentra
	 */
	public Elemento buscar(Identificador id) {
		return _productos.buscar(id);
	}

	/**
	 * 
	 * Busca elementos por un String de busqueda.
	 * @param busqueda String a buscar.
	 * @return la lista de elementos con coincidencias.
	 */
	public List<Elemento> buscar(String busqueda) {
		return _productos.buscar(busqueda);
	}

	/**
	 * Elimina un Elemento por su identificador.
	 * 
	 * @param id Identificador del elemento a eliminar.
	 * @return el elemento eliminado.
	 */
	public Elemento eliminar(Identificador id) {
		return _productos.getComposite().eliminar(id);
	}

	/**
	 * Aplica una estrategia sobre todos los elementos de la tienda.
	 * @param nueva Estrategia a aplicar.
	 */
	public void aplicarEstrategia(EstrategiaPrecio nueva) {
		aplicarEstrategia(_productos.getId(), nueva);
	}

	/**
	 * Aplica una estrategia sobre un Elemento de la tienda.
	 * 
	 * @param id
	 *            Identificador del Elemento sobre el que aplicar la estrategia.
	 * @param nueva
	 *            Estrategia a aplicar.
	 */
	public void aplicarEstrategia(Identificador id, EstrategiaPrecio nueva){
		_productos.buscar(id).setEstrategia(nueva);
	}

	/**
	 * Retira un usuario de la tienda.
	 * @param user Usuario que quitar.
	 * @return true si se le quita con exito.
	 */
	public boolean eliminarUsuario(Usuario user) {
		return _usuarios.remove(user);
	}
	
	/**
	 * Crea la ruta de categorias indicada si no esta ya y devuelve la ultima.
	 * @param parametros Array de String en el que se indica el nombre de las categorias que se deben crear.
	 * @return La ultima categoria creada
	 */
	public Categoria crearCategorias(String[] parametros){
		int numParametros = parametros.length;
		int i = 0;
		Categoria ant = _productos.getComposite();//Categoria anterior
		Elemento act = buscarPorNombre(parametros[i]);//Categoria actual
		/*
		 * Mientras que hay elementos con ese nombre, y son categorias, iteramos.
		 * Cuando el bucle termina, se tiene en ant la ultima categoria del array que
		 * ya hay creada.
		 */
		while((act != null) && (act.getComposite() != null) && (i < numParametros)){
			//La anterior sera la recien buscada
			ant = act.getComposite();
			//Y buscamos la siguiente
			act = buscarPorNombre(ant.getId(),parametros[i]);
			i++;
		}
		/*
		 * Si quedan strings en el array, ahora iteramos para crear las categorias que quedan.
		 */
		for(;i<numParametros;i++){
			try {
				// La creamos
				act = new Categoria(parametros[i],parametros[i]);
				// La anyadimos en la anterior
					ant.anyadir(act);
				// Y hacemos que la anterior sea la recien creada.
				ant = act.getComposite();
			} catch (ParametrosIncorrectosException e) {
				_logger.warn("No se ha podido crear o anyadir la categoria");
			}
		}	
		return ant; // Devolvemos la ultima categoria creada.
	}
	
	/**
	 * Busca un elemento por su nombre dentro de todos los productos de la tienda.
	 * @param nombre Nombre a buscar
	 * @return El primer elemento que coincide con ese nombre.
	 */
	public Elemento buscarPorNombre(String nombre){
		return buscarPorNombre(_productos.getId(),nombre);
	}
	
	/**
	 * Busca un elemento por su nombre dentro del elemento de la tienda que tenga el identificador pasado.
	 * @param id Identificador del elemento en el que buscar.
	 * @param nombre Nombre a buscar.
	 * @return El primer elemento que coincide con ese nombre dentro del elemento con identificador id.
	 */
	public Elemento buscarPorNombre(Identificador id, String nombre){
		Elemento encontrado = null;
		// Primero buscamos donde buscar.
		Elemento en = buscar(id);
		// Si existe un elemento con ese identificador, entonces buscamos el nombre.
		if(en != null){
			// Obtenemos una lista con todas las coincidencias.
			List<Elemento> lista = en.buscar(nombre);
			Iterator<Elemento> itr = lista.iterator();
			boolean acierto = false;
			Elemento actual = null;
			// Buscamos la primera cuyo nombre sea igual al buscado.
			while(itr.hasNext() && !acierto){
				actual = itr.next();
				acierto = nombre.equalsIgnoreCase(actual.getNombre());
			}
			// Si se encontro, entonces lo guardamos para devolverlo
			if(acierto){
				encontrado = actual;
			}
		}
		return encontrado;
	}
	
	/**
	 * Devuelve un iterador sobre todos los productos de la tienda.
	 * @param str Tipo de iterador. "categorias" para Iterador por categorias, o un numero para un iterador por precio.
	 * @return El iterador obtenido.
	 */
	public Iterador<Elemento> darIterador(String str){
		return _productos.darIterador(str);
		
	}
	
	/**
	 * @return la representacion en String de todos los productos.
	 */
	public String toString() {
		return _productos.toString();
	}
}
