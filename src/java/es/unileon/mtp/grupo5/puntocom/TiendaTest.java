package es.unileon.mtp.grupo5.puntocom;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.mtp.grupo5.puntocom.handler.*;
import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.strategyPrecio.*;

/**
 * 
 * Test para la tienda.
 * 
 * @author Mario Carracedo Garcia.
 * @author Alberto Guzman Goyanes.
 * @author Hector Rodriguez Gutierrez.
 * @author Abel Mayorga Gonzalez.
 * @author Pablo Lobato Gonzalez
 * @author Rodrigo Urdiales Santos
 * @version 2.0
 */

public class TiendaTest {

	Tienda _tienda;
	Elemento _producto;
	
	@Before
	public void setUp() throws Exception {
		ContadorIdentificadores.reiniciarContadores();
		_tienda = new Tienda();
		_producto = new ProductoVenta("Mesa cutre", "Una mesa muy cutre", 1f);
		_tienda.anyadir(_producto,null);
	}

	/**
	 * Test que comprueba que los elementos se anyaden correctamente a la tienda.
	 */
	@Test
	public void testAnyadirCorrecto() throws ParametrosIncorrectosException, BadFormatException{
		assertTrue(_tienda.anyadir(new Categoria(new IdentificadorDefecto("2"),"Antiguedades","Todo lo antiguo para su hogar"),(_tienda.buscar(new IdentificadorCategoria("1"))).getId()));
	}

	/**
	 * Test que comprueba que si se intenta eliminar todo el arbol de la tienda, este se devuelve.
	 */
	@Test
	public void testEliminarRaiz() throws BadFormatException{
		assertEquals("1",_tienda.eliminar(new IdentificadorDefecto("1")).getId().toString());
	}
	
	/**
	 * Test que comprueba que tras aplicar una estrategia a toda la tienda, los productos se encuentran rebajados.
	 */
	@Test
	public void testAplicarEstrategia() throws OperacionNoPermitidaException, ParametrosIncorrectosException{
		_tienda.aplicarEstrategia(new Rebaja(0.1f));
		assertEquals(_producto.getPrecio(),0.9f,0);
	}
	
	/**
	 * Test que comprueba que se anyaden los usuarios correctamente y prueba que se busca correctamente.
	 */
	@Test
	public void testAnyadirUsuario(){
		Usuario usr1 = new Usuario("usuario1");
		_tienda.anyadirUsuario(usr1);
		assertTrue(_tienda.contieneUsuario("usuario1"));
	}
	
	/**
	 * Test que elimina un usuario
	 */
	@Test
	public void testEliminarUsuario(){
		Usuario usr1 = new Usuario("usuario1");
		_tienda.anyadirUsuario(usr1);
		_tienda.eliminarUsuario(usr1);
		assertFalse(_tienda.contieneUsuario("usuario1"));
	}
	
	/**
	 * Test que comprueba que el metodo crearCategorias funciona correctamente
	 */
	@Test
	public void testCrearCategorias(){
		String[] parametros = new String[3];
		parametros[0]="Exteriores";
		parametros[1]="Camping";
		parametros[2]="Sillas";
		_tienda.crearCategorias(parametros);
		assertNotNull(_tienda.buscar("Sillas"));
		assertNotNull(_tienda.buscar("Camping"));
		assertNotNull(_tienda.buscar("Exteriores"));
	}	
	
	/**
	 * Test que comprueba que el buscar por nombre funciona correctamente.
	 */
	@Test
	public void testBuscarPorNombre() throws ParametrosIncorrectosException{
		Elemento pr1 = new ProductoSubasta("silla1","silla1",14.2f);
		_tienda.anyadir(pr1, null);
		assertNotNull(_tienda.buscarPorNombre("silla1"));
	}
}
