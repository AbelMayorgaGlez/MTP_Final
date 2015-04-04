package es.unileon.mtp.grupo5.puntocom.command;


import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.*;
import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.ContadorIdentificadores;
import es.unileon.mtp.grupo5.puntocom.interfaz.InterfazUsuario;

/**
 * 
 * Test del comando Bid.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class BidTest {
	Tienda _tienda;
	String[] _parametros1 = new String[3];
	String[] _parametros2 = new String[3];
	InterfazUsuario _interfaz;

	@Before
	public void setUp() throws Exception {
		ContadorIdentificadores.reiniciarContadores();
		_tienda=new Tienda();		
		_parametros1[0]="silla1";
		_parametros1[1]="Pablo";
		_parametros1[2]= "10.7";
		_parametros2[0]= "silla1";
		_parametros2[1]="Alberto";
		_parametros2[2]="17.9";
		
	}
	
	/**
	 * Test que comprueba que se puja correctamente por un elemento
	 */
	@Test
	public void testBid() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException, OperacionNoPermitidaException, BadFormatException{
		Elemento elem = new ProductoSubasta("silla1","descripcion",10.5f);
		Categoria cat1 = new Categoria("Productos","Productos");
		Categoria cat2 = new Categoria ("Sillas","Sillas");
		_tienda.anyadir(cat1,null);
		_tienda.anyadir(cat2, cat1.getId());
		_tienda.anyadir(elem,cat2.getId());
		elem.comenzarSubasta();
		new Bid(_tienda, _parametros1).ejecutar();
		new Bid(_tienda, _parametros2).ejecutar();
		elem.finalizarSubasta();
		assertTrue(_tienda.buscarUsuario("Pablo").getElementosGanados().isEmpty());
		assertFalse(_tienda.buscarUsuario("Alberto").getElementosGanados().isEmpty());	
	}
	
	/**
	 * Test que comprueba que no se puede pujar por un elemento si no esta creado.
	 */
	@Test (expected=ErrorEjecucionException.class)
	public void testBidException() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException{
		new Bid(_tienda, _parametros1).ejecutar();
	}

}
