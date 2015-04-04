package es.unileon.mtp.grupo5.puntocom.command;

import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import org.junit.*;
import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.*;

/**
 * 
 * Test del comando NewProd.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class NewProdTest {
	
	Tienda _tienda;
	String[] _parametros = new String[6];
	
	@Before
	public void setUp() throws Exception {
		ContadorIdentificadores.reiniciarContadores();
		_tienda=new Tienda();
		_parametros[0]="silla1";
		_parametros[1] = "descripcion";
		_parametros[2] = "10.5";
		_parametros[3] = "subasta";
		_parametros[4] = "Productos";
		_parametros[5] = "Sillas";
	}
	
	/**
	 * Test que comprueba que se crea un elemento correctamente.
	 */
	@Test
	public void testNewProd() throws ParametrosIncorrectosException, ErrorEjecucionException, IOException{
		new NewProd(_tienda,_parametros).ejecutar();
		assertNotNull(_tienda.buscar(new IdentificadorDefecto("PS-000001")));
	}
	
	/**
	 * Test que comprueba que no se puede crear un elemento.
	 */
	@Test(expected = ErrorEjecucionException.class)
	public void testNewProdException() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException{
		_parametros[0]="10.5";
		_parametros[1]="10.5";
		_parametros[2]="10.5";
		_parametros[3]="10.5";
		_parametros[4]="10.5";
		_parametros[5]="10.5";
		new NewProd(_tienda,_parametros).ejecutar();
	}
		

}
