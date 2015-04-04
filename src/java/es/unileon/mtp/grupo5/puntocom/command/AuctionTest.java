package es.unileon.mtp.grupo5.puntocom.command;

import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import es.unileon.mtp.grupo5.puntocom.*;
import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.*;

/**
 * 
 * Test del comando Auction.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class AuctionTest {
	
	Tienda _tienda;
	String[] _parametros;
	
	@Before
	public void setUp() throws Exception {
		ContadorIdentificadores.reiniciarContadores();
		_tienda=new Tienda();
		_parametros = new String[1];
		_parametros[0]="PS-000001";		
	}
	/**
	 * Test que comprueba que se saca a subasta correctamente un elemento.
	 */
	@Test
	public void testAuction() throws ParametrosIncorrectosException, OperacionNoPermitidaException, BadFormatException,  ErrorEjecucionException, IOException{
		Elemento elem = new ProductoSubasta("silla1","descripcion",10.5f);
		Categoria cat1 = new Categoria("Productos","Productos");
		Categoria cat2 = new Categoria ("Sillas","Sillas");
		_tienda.anyadir(cat1,null);
		_tienda.anyadir(cat2, cat1.getId());
		_tienda.anyadir(elem,cat2.getId());		
		new Auction(_tienda,_parametros).ejecutar();
		assertTrue(_tienda.buscar(new IdentificadorDefecto("PS-000001")).esSubastaActiva());
	}
	
	/**
	 * Test que comprueba que no se puede sacar un elemento inexistente a subasta
	 */
	@Test(expected = ErrorEjecucionException.class)
	public void testAuctionException() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException{
		new Auction(_tienda,_parametros).ejecutar();
	}

}
