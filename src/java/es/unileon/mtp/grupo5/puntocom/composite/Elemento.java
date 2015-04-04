package es.unileon.mtp.grupo5.puntocom.composite;

import es.unileon.mtp.grupo5.puntocom.Usuario;
import es.unileon.mtp.grupo5.puntocom.handler.*;
import es.unileon.mtp.grupo5.puntocom.iterator.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.observer.*;
import es.unileon.mtp.grupo5.puntocom.strategyIterador.*;
import es.unileon.mtp.grupo5.puntocom.strategyPrecio.*;

import java.util.List;


/**
 * Componente del patron Composite. Establece los metodos que se pueden usar desde sus hojas y
 * establece un comportamiento por defecto para algunos de ellos, el cual es lanzar
 * OperacionNoPermitidaException.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 3.0
 */
public abstract class Elemento implements Sujeto, Agregado<Elemento>{

	/**
	 * Busca un elemento en el composite por su identificador.
	 * @param id Identificador del elemento a buscar.
	 * @return el elemento si se encuentra, o null en caso contrario.
	 */
	public abstract Elemento buscar(Identificador id);

	/**
	 * Busca elementos que coincidan con el String pasado.
	 * @param busqueda String a buscar.
	 * @return una lista con los elementos encontrados.
	 */
	public abstract List<Elemento> buscar(String busqueda);
	
	/**
	 * Comprueba si el elemento actual esta en venta.
	 * @return true cuando el elemento esta en venta, false de lo contrario.
	 * @throws OperacionNoPermitidaException si el elemento actual es un producto de subasta.
	 */
	public boolean esVentaActiva() throws OperacionNoPermitidaException{
		throw new OperacionNoPermitidaException("Solo puede estar a la venta un producto de venta.\n");
	}
	
	/**
	 * Devuelve el numero de productos que hay en el elemento actual.
	 * @return el numero de productos que hay en el elemento actual.
	 */
	public abstract int getCantidad();
	
	/**
	 * Devuelve la descripcion del elemento actual.
	 * @return la descripcion del elemento actual.
	 */
	public abstract String getDescripcion();
	
	/**
	 * Devuelve el identificador del elemento actual.
	 * @return el identificador del elemento actual.
	 */
	public abstract Identificador getId();
	
	/**
	 * Devuelve el nombre del elemento actual.
	 * @return el nombre del elemento actual.
	 */
	public abstract String getNombre();
	
	/**
	 * Devuelve el elemento padre del elemento actual.
	 * @return el elemento padre del elemento actual.
	 */
	public abstract Categoria getPadre();
	
	/**
	 * Devuelve el precio del elemento actual.
	 * @return el precio del elemento actual.
	 */
	public abstract float getPrecio();
	
	/**
	 * Devuelve una lista con todos los productos que hay dentro del elemento actual.
	 * @return la lista de los productos.
	 */
	public abstract List<Elemento> getProductos();

	@Override
	public void notificar() throws OperacionNoPermitidaException{
		throw new OperacionNoPermitidaException("Solo se puede notificar el cambio de un producto de subasta.\n");
	}
	
	/**
	 * Saca a la venta al elemento actual.
	 * @throws OperacionNoPermitidaException Si el elemento actual es un producto de subasta.
	 */
	public void sacarAVenta() throws OperacionNoPermitidaException{
		throw new OperacionNoPermitidaException("Solo se puede sacar a la venta a un ProductoVenta.\n");
	}

	/**
	 * Establece una estrategia de calculo de precio al elemento actual.
	 * @param nueva es la estrategia nueva.
	 */
	public abstract void setEstrategia(EstrategiaPrecio nueva);
	
	/**
	 * Establece la descripcion del elemento actual.
	 * @param descripcion Descripcion para el elemento actual.
	 */
	public abstract void setDescripcion(String descripcion);
	
	/**
	 * Establece el nombre del elemento actual.
	 * @param nombre Nombre para el elemento actual.
	 */
	public abstract void setNombre(String nombre);
	
	/**
	 * Establece el precio para el producto actual.
	 * @param precio Precio para el producto actual.
	 * @throws ParametrosIncorrectosException si el precio es negativo.
	 * @throws OperacionNoPermitidaException si el elemento actual no admite cambios en su precio.
	 */
	public void setPrecio(float precio) throws ParametrosIncorrectosException, OperacionNoPermitidaException{
		throw new OperacionNoPermitidaException("No se puede asignar el precio a una categoria.\n");
	}
	
	/**
	 * Establece el padre del elemento actual.
	 * @param padre Elemento padre para el elemento actual.
	 */
	protected abstract void setPadre(Categoria padre);
	
	/**
	 * Finaliza la venta del elemento actual.
	 * @throws OperacionNoPermitidaException Si el elemento actual es un ProductoSubasta.
	 */
	public void terminarVenta() throws OperacionNoPermitidaException{
		throw new OperacionNoPermitidaException("Solo puede estar a la venta un producto de venta.\n");
	}
	
	/**
	 * Devuelve la representacion en String del elemento actual.
	 * @return la representacion en String del elemento actual.
	 */
	public abstract String toString();

	@Override
	public boolean anyadirObservador(Observador nuevo) throws OperacionNoPermitidaException{
		throw new OperacionNoPermitidaException("Los observadores solo se aplican a los productos de subasta.\n");
	}
	
	@Override
	public boolean eliminarObservador(Observador obs) throws OperacionNoPermitidaException{
		throw new OperacionNoPermitidaException("Los observadores solo se aplican a los productos de subasta.\n");
	}
	
	/**
	 * Comprueba si la subasta esta activa.
	 * @return true si esta activa, false si no.
	 * @throws OperacionNoPermitidaException Si el elemento actual no puede subastarse.
	 */
	public boolean esSubastaActiva() throws OperacionNoPermitidaException{
		throw new OperacionNoPermitidaException("Solo los productos de subasta pueden subastarse.\n");
	}

	/**
	 * Finaliza la subasta del elemento actual.
	 * @throws OperacionNoPermitidaException Si el elemento actual no puede subastarse.
	 */
	public void finalizarSubasta() throws OperacionNoPermitidaException{
		throw new OperacionNoPermitidaException("Solo los productos de subasta pueden subastarse.\n");
	}
	
	/**
	 * Comienza la subasta del elemento actual.
	 * @throws OperacionNoPermitidaException Si el elemento actual no puede subastarse.
	 */
	public void comenzarSubasta() throws OperacionNoPermitidaException{
		throw new OperacionNoPermitidaException("Solo los productos de subasta pueden subastarse.\n");
	}
	
	/**
	 * Puja por un producto en subasta.
	 * @param cantidad cantidad de dinero por la que se puja.
	 * @param usuario Usuaro que puja.
	 * @return true en caso de que la puja tenga exito, false en caso contrario.
	 * @throws OperacionNoPermitidaException si el elemento actual no puede subastarse.
	 */
	public boolean pujar(float cantidad, Usuario usuario)throws OperacionNoPermitidaException{
		throw new OperacionNoPermitidaException("Solo se puede pujar por un producto de subasta.\n");
	}
	
	/**
	 * Devuelve el ganador de la subasta del elemento actual.
	 * @return El usuario ganador.
	 * @throws OperacionNoPermitidaException Si el elemento actual no puede subastarse.
	 */
	public Usuario getGanadorSubasta() throws OperacionNoPermitidaException{
		throw new OperacionNoPermitidaException("Solo se puede pujar por un producto de subasta.\n");
	}
	
	/**
	 * Obtiene la puja mas alta realizada por el elemento actual.
	 * @return la puja mas alta realizada por el producto de subasta actual.
	 * @throws OperacionNoPermitidaException si el elemento actual no puede subastarse.
	 */
	public float getPujaMasAlta()throws OperacionNoPermitidaException{
		throw new OperacionNoPermitidaException("Solo se puede obtener la puja mas alta de un producto de subasta.\n");
	}
	
	/**
	 * Realiza el cast a la clase Categoria del elemento actual. 
	 * @return null si el elemento actual no es una categoria, y el elemento actual si si lo es.
	 */
	public Categoria getComposite(){
		return null;
	}
	
	/**
	 * Devuelve un iterador sobre el elemento actual.
	 * @param arg Tipo de iterador. 
	 * 		"categorias" para un iterador por categorias, un precio para un iterador por precios.
	 * 		En caso de que sea otra cosa, o el elemento actual un Producto, entonces se devolvera IteradorNulo<Elemento>
	 * @return El iterador pedido o IteradorNulo en caso de que no exista el tipo de iterador pedido.
	 */
	public abstract Iterador<Elemento> darIterador(String arg);
	
	/**
	 * Devuelve un iterador sobre el elemento actual.
	 * @param est Estrategia de seleccion para el iterador.
	 * @return El iterador creado.
	 */
	public abstract Iterador<Elemento> darIterador(EstrategiaIteradorComposite est);
	
	/**
	 * @return el numero objetos que cuelgan del elemento actual en el arbol de objetos.
	 */
	public abstract int darNumHijos();
	
	/**
	 * Devuelve el hijo en la posicion i.
	 * @param i Posicion del hijo deseado.
	 * @return El hijo en la posicion i.
	 */
	public abstract Elemento darHijo(int i);
}