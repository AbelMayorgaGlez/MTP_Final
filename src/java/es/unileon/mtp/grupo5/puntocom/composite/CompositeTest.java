package es.unileon.mtp.grupo5.puntocom.composite;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import es.unileon.mtp.grupo5.puntocom.handler.*;
import es.unileon.mtp.grupo5.puntocom.strategyPrecio.Rebaja;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Test para los elementos del composite.
 * 
 * @author Mario Carracedo Garcia.
 * @author Alberto Guzman Goyanes.
 * @author Hector Rodriguez Gutierrez.
 * @author Abel Mayorga Gonzalez.
 * @author Pablo Lobato Gonzalez
 * @author Rodrigo Urdiales Santos
 * @version 1.0
 */
public class CompositeTest {

	Elemento _raiz, _cat1, _cat2, _pr1, _pr2, _pr3;

	@Before
	public void setUp() throws Exception {
		ContadorIdentificadores.reiniciarContadores();
		_raiz = new Categoria("Almacen","Almacen");
		_cat1 = new Categoria("Mesas", "Mesas");
		_cat2 = new Categoria("Sillas","Sillas");
		_pr1 = new ProductoVenta(new IdentificadorDefecto("PV-000001"), "Mesa1",
				"Mesa con bonitas flores", 30.5f);
		_pr2 = new ProductoSubasta(new IdentificadorDefecto("PS-000001"), "Silla1",
				"Silla con 3 patas", 10.5f);
		_pr3 = new ProductoVenta(new IdentificadorDefecto("PV-000002"), "Mesa2",
				"Mesa de camping", 30.2f);
		_raiz.getComposite().anyadir(_cat1);
		_raiz.getComposite().anyadir(_cat2);
		_cat1.getComposite().anyadir(_pr1);
		_cat1.getComposite().anyadir(_pr3);
		_cat2.getComposite().anyadir(_pr2);
	}

	/**
	 * Test que comprueba que salta la excepcion 
	 * cuando se intenta crear una categoria con un identificador incorrecto.
	 */
	@Test(expected = ParametrosIncorrectosException.class)
	public void testConstructorExcepcion() throws BadFormatException, ParametrosIncorrectosException {
		new Categoria(new IdentificadorDefecto("PS-000001"), "Nuevo", "Nuevo");
	}
	
	/**
	 * Test que comprueba que al crear un elemento, su padre se establece a null.
	 */
	@Test
	public void testCrearElementoYPadreNull() throws ParametrosIncorrectosException, BadFormatException{
		Elemento nuevo = new Categoria(new IdentificadorDefecto("4"), "", "");
		assertEquals(null, nuevo.getPadre());
	}
	
	/**
	 * Test que comprueba que los padres se asignan correctamente tras anyadir un elemento.
	 */
	@Test
	public void testAnyadirPadreCorrecto() throws OperacionNoPermitidaException{
		assertEquals(_cat1,_pr3.getPadre());
		_cat2.getComposite().anyadir(_pr3);
		assertEquals(_cat2, _pr3.getPadre());
	}
	/**
	 * Test que comprueba que no se puede anyadir null al composite.
	 */
	@Test
	public void testAnyadirNullFalse() throws OperacionNoPermitidaException{
		assertFalse(_raiz.getComposite().anyadir(null));
	}
	
	/**
	 * Test que comprueba que no se puede anyadir a una categoria un elemento que ya contiene.
	 */
	@Test
	public void testAnyadirRepetidoFalse() throws OperacionNoPermitidaException{
		assertFalse(_raiz.getComposite().anyadir(_cat1));
	}

	/**
	 * Test que comprueba que se devuelve null al buscar un elemento que no esta.
	 */
	@Test
	public void testBuscarIdentificadorQueNoEsta() throws BadFormatException {
		assertNull(_raiz.buscar(new IdentificadorDefecto("4")));
	}

	/**
	 * Test que comprueba que se encuentra el elemento buscado si esta en el primer nivel.
	 */
	@Test
	public void testBuscarIdentificadorNivel1() throws BadFormatException {
		assertEquals("2", _raiz.buscar(new IdentificadorDefecto("2")).getId().toString());
	}

	/**
	 * Test que comprueba que se encuentra el elemento si esta a un nivel mas profundo.
	 */
	@Test
	public void testBuscarIdentificadorProfundo() throws BadFormatException {
		assertEquals("PS-000001", _raiz.buscar(new IdentificadorDefecto("PS-000001")).getId().toString());
	}

	/**
	 * Test que comprueba que si se busca por String algo que no esta, devuelve una lista vacia.
	 */
	@Test
	public void testBuscarStringSinCoincidencias() {
		List<Elemento> lista = new ArrayList<Elemento>();
		lista = _raiz.buscar("Coche");
		assertTrue(lista.isEmpty());
	}

	/**
	 * Test que comprueba que al buscar por String se devuelven los elementos que debe encontrar.
	 */
	@Test
	public void testBuscarStringConCoincidencias() {
		List<Elemento> lista = new ArrayList<Elemento>();
		lista = _raiz.buscar("Silla");
		assertEquals(lista.size(),2);
		assertEquals(_cat2,lista.get(0));
		assertEquals(_pr2,lista.get(1));
	}

	/**
	 * Test que comprueba que no se elimina nada si queremos eliminar algo que no esta.
	 */
	@Test
	public void testEliminarIncorrecto() throws BadFormatException, OperacionNoPermitidaException {
		assertNull(_raiz.getComposite().eliminar(new IdentificadorDefecto("PV-000006")));
	}

	/**
	 * Test que comprueba que se elimina un elemento correctamente.
	 */
	@Test
	public void testEliminarCorrecto() throws BadFormatException, OperacionNoPermitidaException {
		assertEquals("PS-000001", _raiz.getComposite().eliminar(new IdentificadorPS("PS-000001")).getId().toString());
	}

	/**
	 * Test que comprueba que si se intenta eliminar la raiz de un arbol de elementos,
	 * esta se devuelve.
	 */
	@Test
	public void testEliminarRaiz() throws BadFormatException, OperacionNoPermitidaException {
		assertEquals(_raiz, _raiz.getComposite().eliminar(_raiz.getId()));
	}

	/**
	 * Test que comprueba que se cuentan correctamente los productos con getCantidad().
	 */
	@Test
	public void testgetCantidad() {
		assertEquals(3, _raiz.getCantidad());
	}

	/**
	 * Test que comprueba que el precio de una categoria se calcula correctamente.
	 */
	@Test
	public void testgetPrecio() {
		assertEquals(60.7f, _cat1.getPrecio(), 0f);
	}

	/**
	 * Test que comprueba que la lista de productos se obtiene correctamente.
	 */
	@Test
	public void testgetProductos() {
		List<Elemento> lista = new ArrayList<Elemento>();
		lista.add(_pr1);
		lista.add(_pr3);
		assertEquals(lista,_cat1.getProductos());
	}

	/**
	 * Test que comprueba que el String se genera como debe ser.
	 */
	@Test
	public void testToString() {
		String control = _raiz.toString();
		assertEquals("\nAlmacen: Almacen:\n\t-" + "\nMesas: Mesas:\n"
				+ "\t-Mesa1: Mesa con bonitas flores,"
				+ " 30.5.\n\t-Mesa2: Mesa de camping, 30.2.\n\t-"
				+ "\nSillas: Sillas:"
				+ "\n\t-Silla1: Silla con 3 patas, 10.5.\n", control);
	}

	/**
	 * Test que comprueba que se puede pujar por un precio mayor.
	 */
	@Test
	public void testPujarMayor() throws OperacionNoPermitidaException {
		_pr2.comenzarSubasta();
		assertEquals(true, _pr2.pujar(10.6f,null));
	}
	
	/**
	 * Test que comprueba que no se puede pujar por un precio menor.
	 */
	@Test
	public void testPujarMenor() throws OperacionNoPermitidaException {
		assertEquals(false, _pr2.pujar(9f,null));
	}

	/**
	 * Test que comprueba que se puede convertir un producto de venta en uno de subasta.
	 */
	@Test
	public void testConvertirVentaASubasta() throws ParametrosIncorrectosException, BadFormatException {
		new ProductoSubasta(new IdentificadorDefecto("PS-000002"), _pr1);
	}
	
	/**
	 * Test que comprueba que se puede convertir un producto de subasta en uno de venta.
	 */
	@Test
	public void testConvertirSubastaAVenta() throws ParametrosIncorrectosException, BadFormatException {
		new ProductoVenta(new IdentificadorDefecto("PV-000003"), _pr2);
	}


	/**
	 * Test que comprueba que salta ParametrosIncorrectosException cuando el identificador
	 * que se pasa no es del tipo correcto.
	 */
	@Test(expected = ParametrosIncorrectosException.class)
	public void testConvertirExcepcion() throws ParametrosIncorrectosException, BadFormatException {
		new ProductoSubasta(new IdentificadorDefecto("PV-000003"), _pr1);
	}
	
	/**
	 * Test que comprueba que tras aplicar una estrategia de rebaja a un producto, este se encuentra rebajado.
	 * @throws ParametrosIncorrectosException 
	 * @throws OperacionNoPermitidaException 
	 */
	@Test
	public void testPrecioRebajado() throws OperacionNoPermitidaException, ParametrosIncorrectosException{
		_pr1.setEstrategia(new Rebaja(0.1f));
		assertEquals(30.5f*0.9f,_pr1.getPrecio(),0);
	}


}
