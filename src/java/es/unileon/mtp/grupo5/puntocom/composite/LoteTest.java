package es.unileon.mtp.grupo5.puntocom.composite;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import es.unileon.mtp.grupo5.puntocom.handler.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;

/**
 * 
 * Test para los lotes.
 * 
 * @author Mario Carracedo Garcia.
 * @author Alberto Guzman Goyanes.
 * @author Hector Rodriguez Gutierrez.
 * @author Abel Mayorga Gonzalez.
 * @author Pablo Lobato Gonzalez
 * @author Rodrigo Urdiales Santos
 * @version 1.0
 */

public class LoteTest {
	
	Elemento _raiz, _cat1, _cat2, _pr1, _pr2, _pr3, _lote;
	@Before
	public void setUp() throws Exception {
		ContadorIdentificadores.reiniciarContadores();
		_raiz = new Categoria("Almacen", "Almacen");
		_cat1 = new Categoria("Mesas", "Mesas");
		_cat2 = new Categoria("Sillas", "Sillas");
		_pr1 = new ProductoVenta(new IdentificadorDefecto("PV-000001"), "Mesa1", "Mesa con bonitas flores", 30.5f);
		_pr2 = new ProductoSubasta(new IdentificadorDefecto("PS-000001"), "Silla1", "Silla con 3 patas", 10.5f);
		_pr3 = new ProductoVenta(new IdentificadorDefecto("PV-000002"), "Mesa2", "Mesa de camping", 30.2f);
		_raiz.getComposite().anyadir(_cat1);
		_raiz.getComposite().anyadir(_cat2);
		_cat1.getComposite().anyadir(_pr1);
		_cat1.getComposite().anyadir(_pr3);
		_cat2.getComposite().anyadir(_pr2);
		_lote= new Lote(new IdentificadorDefecto("LT-1"),"Lote1","Lote Prueba");
	}

	/**
	 * Test que comprueba que no se puede construir un lote con un identificador incorrecto.
	 */
	@Test(expected = ParametrosIncorrectosException.class)
	public void testLoteIdStringString() throws ParametrosIncorrectosException {
		new Lote(new IdentificadorDefecto("PS-1"),"lo","pru");
		
	}
	
	/**
	 * Test que comprueba que no se pueden meter en los lotes productos de subasta.
	 */
	@Test
	public void testEsValido() throws ParametrosIncorrectosException, BadFormatException, OperacionNoPermitidaException {
		assertFalse(_lote.getComposite().anyadir(new ProductoSubasta(new IdentificadorDefecto("PS-000002"),"Nombre","Descr",2.3f)));
	}

	/**
	 * Test que comprueba que al lote se pueden anyadir productos de venta.
	 */
	@Test
	public void testAnyadirElemento() throws BadFormatException, ParametrosIncorrectosException, OperacionNoPermitidaException{
		Elemento elem = new ProductoVenta(new IdentificadorPV("PV-000003"),"Silla","Sillaca",3.1f);
		assertTrue(_lote.getComposite().anyadir(elem));
	}
	
	/**
	 * Test que comprueba que si anyadimos todo al lote, todos los productos
	 * de venta se mueven al lote y el resto permanece en su sitio.
	 */
	@Test
	public void testAnyadirTodoAlLote() throws OperacionNoPermitidaException{
		_lote.getComposite().anyadir(_raiz);
		assertEquals(2,_lote.getCantidad());
		assertEquals(0,_cat1.getCantidad());
	}

}
