package es.unileon.mtp.grupo5.puntocom.composite;

import es.unileon.mtp.grupo5.puntocom.handler.*;
import es.unileon.mtp.grupo5.puntocom.iterator.*;
import es.unileon.mtp.grupo5.puntocom.strategyIterador.*;
import es.unileon.mtp.grupo5.puntocom.strategyPrecio.EstrategiaPrecio;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;

import java.util.*;

import org.apache.log4j.Logger;

/**
 * 
 * Compuesto del patron Composite.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 3.0
 */

public class Categoria extends Elemento {

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
	 * Elemento padre del actual.
	 */
	protected Categoria _padre;

	/**
	 * Lista de elementos que contiene.
	 */
	protected List<Elemento> _elementos;

	/**
	 * Logger de la clase.
	 */
	static Logger _logger = Logger.getLogger(Categoria.class);
	
	protected Categoria(){}

	/**
	 * Constructor de clase. Asigna los identificadores de forma secuencial.
	 * @param nombre Nombre de la categoria.
	 * @param descr Descripcion de la categoria.
	 */
	public Categoria(String nombre, String descr) throws ParametrosIncorrectosException{
		this(new IdentificadorCategoria(),nombre, descr);
	}
		
	/**
	 * Constructor de clase.
	 * 
	 * @param id
	 *            Identificador para la categoria.
	 * @param nombre
	 *            Nombre de la categoria.
	 * @param descr
	 *            Descripcion de la categoria.
	 * @throws ParametrosIncorrectosException
	 *             si id no es un IdentificadorCategoria.
	 */
	public Categoria(Identificador id, String nombre, String descr)
			throws ParametrosIncorrectosException {
		_nombre = nombre;
		_descripcion = descr;
		_elementos = new ArrayList<Elemento>();
		try {
			_id = new IdentificadorCategoria(id);
		} catch (BadFormatException e) {
			_logger.warn("Se ha intentado crear una Categoria con un identificador que no es IdentificadorCategoria.");
			throw new ParametrosIncorrectosException(
					"El identificador debe ser un identificador de categoria.\n");
		}
		_logger.info("Categoria " + _nombre + " creada con exito.");
	}

	/**
	 * Anyade un elemento a esta categoria.
	 * @param elem Elemento a anyadir.
	 * @return true si se ha anyadido con exito, false de lo contrario.
	 */
	public boolean anyadir(Elemento elem) {
		boolean exito = false;
		/*
		 * Si el elemento no esta en la categoria
		 * y no es null, se puede anyadir.
		 */
		if (!_elementos.contains(elem) && (elem != null)) {
			_elementos.add(elem);
			_logger.info("Elemento " + elem.getNombre()
					+ "anyadido a la categoria " + _nombre);
			/*
			 * Lo quitamos de donde estaba y cambiamos
			 * su objeto padre.
			 */
			if (elem.getPadre() != null) {
				elem.getPadre().eliminar(elem.getId());
			}
			elem.setPadre(this);
			exito = true;
		} else {
			_logger.warn("No se ha podido anyadir");
		}
		return exito;
	}

	@Override
	public Elemento buscar(Identificador id) {
		Elemento buscado = null;
		// Si soy yo, me devuelvo.
		if (this.getId().compareTo(id) == 0) {
			_logger.info("Identificador encontrado: elemento " + _nombre);
			buscado = this; 
		// Si no, lo busco entre mis hijos.
		} else { 
			Iterator<Elemento> iterador = _elementos.iterator();
			while ((buscado == null) && iterador.hasNext()) {
				buscado = iterador.next().buscar(id);
			}
		}
		return buscado;
	}

	@Override
	public List<Elemento> buscar(String busqueda) {
		List<Elemento> lista = new ArrayList<Elemento>();
		/*
		 * Poniendo el else evitamos que se anyada 2 veces en caso de que
		 * busqueda pertenezca a nombre y descripcion a la vez.
		 */
		// Compruebo si puede ser el elemento actual buscando en nombre y descripcion
		if (_nombre.toLowerCase().indexOf(busqueda.toLowerCase()) != -1) {
			lista.add(this);
		} else if (_descripcion.toLowerCase().indexOf(busqueda.toLowerCase()) != -1) {
			lista.add(this);
		}
		//Si la lista no esta vacia, es que el elemento actual encaja.
		if (!lista.isEmpty()) {
			_logger.info("Se ha encontrado una coincidencia para " + busqueda
					+ " en el elemento " + _nombre);
		}
		// Compruebo entre sus hijos. Si las listas son vacias, no se anyade nada a la lista total.
		for (Elemento elem : _elementos) {
			lista.addAll(elem.buscar(busqueda));
		}
		return lista;
	}

	/**
	 * Elimina un elemento del arbol de objetos.
	 * @param id Identificador del elemento a eliminar.
	 * @return El elemento eliminado.
	 */
	public Elemento eliminar(Identificador id) {
		// Buscamos el elemento a eliminar
		Elemento buscado = this.buscar(id);

		Elemento aDevolver = null;
		if (buscado == null) {
			// Si no se encuentra, se devuelve null
			_logger.warn("El elemento a eliminar no se encuentra.");
		} else if ((buscado == this) && (_padre == null)) {
			// Si el elemento a eliminar es el actual y ademas es la raiz de un
			// arbol, se devuelve a si mismo.
			_logger.info("Se ha intentado eliminar el nodo padre de un arbol.");
			aDevolver = this;
		} else if (_elementos.remove(buscado)) {
			/*
			 * En el caso de que el elemento a eliminar sea uno del nivel
			 * inferior al actual, se descuelga de arbol y su padre se establece
			 * a null. Luego se devuelve el elemento buscado.
			 */
			_logger.info("Elemento encontrado y eliminado del arbol.");
			buscado.setPadre(null);
			aDevolver = buscado;
		} else {
			/*
			 * En el caso de que el elemento este en un nodo mas profundo al
			 * actual, llamamos a eliminar del padre del elemento a eliminar, y
			 * entonces la recursion termina por el tercer camino de este
			 * metodo.
			 */
			aDevolver = buscado.getPadre().eliminar(id);
		}
		return aDevolver;
	}

	/**
	 * Comprueba si esta a la venta algun elemento de la categoria.
	 * 
	 * @return true si algun elemento de la categoria esta a la venta.
	 */
	@Override
	public boolean esVentaActiva() {
		boolean activa = false;
		Iterator<Elemento> it = _elementos.iterator();
		while (!activa && it.hasNext()) {
			try {
				activa |= it.next().esVentaActiva();
			} catch (OperacionNoPermitidaException e) {
				_logger.warn("Se ha intentado saber si un producto de subasta esta a la venta");
			}
		}
		return activa;
	}
	
	/**
	 * Comprueba si esta subastandose algun elemento de la categoria.
	 * 
	 * @return true si algun elemento de la categoria esta subastandose.
	 */
	@Override
	public boolean esSubastaActiva(){
		boolean activa = false;
		Iterator<Elemento> it = _elementos.iterator();
		while(!activa && it.hasNext()){
			try{
				activa |= it.next().esSubastaActiva();
			} catch (OperacionNoPermitidaException e){
				_logger.warn("Se ha intentado saber si un producto venta tiene activa la subasta");
			}
		}
		return activa;
	}

	/**
	 * Cuenta el numero de productos que incluye.
	 */
	@Override
	public int getCantidad() {
		int cant = 0;
		for (Elemento e : _elementos) {
			cant += e.getCantidad();
		}
		return cant;
	}

	@Override
	public String getDescripcion() {
		return _descripcion;
	}

	@Override
	public Identificador getId() {
		return _id;
	}

	@Override
	public String getNombre() {
		return _nombre;
	}

	@Override
	public Categoria getPadre() {
		return _padre;
	}

	/**
	 * Suma el valor de los productos que incluye y lo devuelve.
	 * 
	 * @return suma del precio de los productos que incluye.
	 */
	@Override
	public float getPrecio() {
		float precio = 0.0f;
		for (Elemento e : _elementos) {
			precio += e.getPrecio();
		}
		return precio;
	}

	@Override
	public List<Elemento> getProductos() {
		List<Elemento> lista = new ArrayList<Elemento>();
		for (Elemento e : _elementos) {
			lista.addAll(e.getProductos());
		}
		return lista;
	}

	/**
	 * Pone en venta todos los elementos que incluye.
	 */
	@Override
	public void sacarAVenta() {
		for (Elemento elem : _elementos) {
			try {
				elem.sacarAVenta();
			} catch (OperacionNoPermitidaException e) {
				_logger.warn("Se ha intentado poner a venta un producto subasta");
				
			}
		}
	}

	@Override
	public void setDescripcion(String descripcion) {
		_descripcion = descripcion;
	}

	@Override
	public void setNombre(String nombre) {
		_nombre = nombre;
	}

	@Override
	protected void setPadre(Categoria padre) {
		_padre = (Categoria) padre;
	}

	/**
	 * Termina la venta de todos los elementos que incluye.
	 */
	@Override
	public void terminarVenta() {
		for (Elemento elem : _elementos) {
			try {
				elem.terminarVenta();
			} catch (OperacionNoPermitidaException e) {
				_logger.warn("Se ha intentado quitar de venta un producto de subasta");
			}
		}
	}

	@Override
	public String toString() {
		String cadena = "\n" + _nombre + ": " + _descripcion + ":\n";
		for (Elemento e : _elementos) {
			cadena += "\t-" + e.toString();
		}
		return cadena;
	}

	/**
	 * Aplica la estrategia a todos sus elementos hijos.
	 * 
	 * @param nueva
	 *            Estrategia a aplicar.
	 */
	@Override
	public void setEstrategia(EstrategiaPrecio nueva) {
		for (Elemento e : _elementos) {
			e.setEstrategia(nueva);
		}
	}

	/**
	 * Inicializa la subasta de todos los productos que contiene.
	 */
	@Override
	public void comenzarSubasta() {
		for (Elemento elem : _elementos) {
			try {
				elem.comenzarSubasta();
			} catch (OperacionNoPermitidaException e) {
				_logger.warn("Se ha intentado sacar a subasta un producto venta");
				
			}
		}
	}

	/**
	 * Finaliza la subasta de todos los productos que contiene.
	 */
	@Override
	public void finalizarSubasta() {
		for (Elemento elem : _elementos) {
			try {
				elem.finalizarSubasta();
			} catch (OperacionNoPermitidaException e) {
				_logger.warn("Se ha intentado quitar de subasta un producto de venta");
			}
		}
	}
	
	@Override
	public Categoria getComposite(){
		return this;
	}
	
	@Override
	public Iterador<Elemento> darIterador(String arg){
		EstrategiaIteradorComposite est = null;
		// Si arg == categorias, entonces la estrategia sera la de un iterador por categorias
		if(arg.equalsIgnoreCase("categorias")){
			est = new EstrategiaIteradorCategoria();
		} else {
			try {
				//Si arg es un numero, entonces la estrategia es la de un iterador por Precio
				float precio = Float.parseFloat(arg);
				est = new EstrategiaIteradorPrecio(precio);
			} catch (NumberFormatException e){
				_logger.warn("No se ha podido convertir el precio");
			}
		}
		return darIterador(est);
	}
	
	@Override
	public Iterador<Elemento> darIterador(EstrategiaIteradorComposite est){
		Iterador<Elemento> it = null;
		// Si la estrategia es nula, entonces no recorremos por nada y damos el IteradorNulo.
		if(est == null){
			it = new IteradorNulo<Elemento>();
		// Si no, damos el iterador con esa estrategia.
		} else {
			it = new IteradorComposite(this,est);
		}
		return it;
	}
	
	@Override
	public int darNumHijos(){
		return _elementos.size();
	}
	
	@Override
	public Elemento darHijo(int i){
		return _elementos.get(i);
	}
}
