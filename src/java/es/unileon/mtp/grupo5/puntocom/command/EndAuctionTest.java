package es.unileon.mtp.grupo5.puntocom.command;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.*;

/**
 * 
 * Test del comando EndAuction.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class EndAuctionTest {
	Tienda _tienda;
	String[] _parametros = new String[1];
	@Before
	public void setUp() throws Exception {
		ContadorIdentificadores.reiniciarContadores();
		_tienda=new Tienda();
		_parametros[0] = "silla1";
	}
	
	/**
	 * Test que comprueba que se quita de subasta correctamente un elemento.
	 */
	@Test
	public void testEndAuction() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException, OperacionNoPermitidaException, BadFormatException{
		Elemento elem = new ProductoSubasta("silla1","descripcion",10.5f);
		Categoria cat1 = new Categoria("Productos","Productos");
		Categoria cat2 = new Categoria ("Sillas","Sillas");
		_tienda.anyadir(cat1,null);;
		_tienda.anyadir(cat2, cat1.getId());
		_tienda.anyadir(elem,cat2.getId());
		elem.comenzarSubasta();
		new EndAuction(_tienda,_parametros).ejecutar();
		assertFalse(_tienda.buscar(new IdentificadorDefecto("PS-000001")).esSubastaActiva());
	}
	
	/**
	 * Test que comprueba que no se puede quitar de subasta un elemento no creado
	 */
	@Test(expected = ErrorEjecucionException.class)
	public void testEndAuctionException() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException{
		new EndAuction(_tienda,_parametros).ejecutar();
	}
	
	
}
