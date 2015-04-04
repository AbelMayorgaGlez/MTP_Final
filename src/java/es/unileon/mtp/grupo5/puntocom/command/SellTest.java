package es.unileon.mtp.grupo5.puntocom.command;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.*;
import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.*;

/**
 * 
 * Test del comando Sell.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class SellTest {
	Tienda _tienda;
	String[] _parametros = new String[1];
	
	@Before
	public void setUp() throws Exception {
		ContadorIdentificadores.reiniciarContadores();
		_tienda=new Tienda();
		_parametros[0]="silla2";		
	}
	
	/**
	 * Test que comprueba que se saca a venta correctamente un producto de venta.
	 */
	@Test
	public void testSell() throws ParametrosIncorrectosException, OperacionNoPermitidaException,  ErrorEjecucionException, IOException{
		Elemento silla2=new ProductoVenta("silla2","silla",20.5f);
		_tienda.anyadir(silla2,null);
		new Sell(_tienda,_parametros).ejecutar();
		assertTrue(_tienda.buscar(new IdentificadorDefecto("PV-000001")).esVentaActiva());
	}
	
	/**
	 * Test que comprueba que no se puede sacar a venta un elemento inexistente.
	 */
	@Test (expected=ErrorEjecucionException.class)
	public void testSellException() throws ParametrosIncorrectosException,  OperacionNoPermitidaException, ErrorEjecucionException, IOException{
		new Sell(_tienda,_parametros).ejecutar();		
	}

}
