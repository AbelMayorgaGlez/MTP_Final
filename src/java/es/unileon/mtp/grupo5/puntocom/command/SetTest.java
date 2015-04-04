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
 * Test del comando Set.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class SetTest {
	Tienda _tienda;
	String[] _parametros= new String[2];
	@Before
	public void setUp() throws Exception {
		ContadorIdentificadores.reiniciarContadores();
		_tienda=new Tienda();		
		Elemento silla2=new ProductoVenta("silla2","silla",20.5f);
		Elemento silla1 = new ProductoSubasta("silla1","descripcion",10.5f);
		_tienda.anyadir(silla2,null);	
		_tienda.anyadir(silla1,null);
	}
	/**
	 * Test que comprueba que se cambia correctamente el nombre de un producto.
	 */
	@Test
	public void testSetNombre() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException{
		_parametros[0]="PV-000001";
		_parametros[1]="silla3";			
		new SetNombre(_tienda,_parametros).ejecutar();
		assertEquals("silla3",_tienda.buscar(new IdentificadorDefecto("PV-000001")).getNombre());
	}
	
	/**
	 * Test que comprueba que si no hay un producto en la tienda con ese identificador no se puede cambiar el nombre.
	 */
	@Test (expected=ErrorEjecucionException.class)
	public void testSetNombreException() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException{
		_parametros[0]="PV-000102";
		new SetNombre(_tienda,_parametros).ejecutar();
	}
	
	/**
	 * Test que comprueba que se cambia correctamente la descripcion de un elemento.
	 */
	@Test
	public void testSetDescripcion() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException{
		_parametros[0]="PV-000001";
		_parametros[1]="Una silla fea";			
		new SetDescripcion(_tienda,_parametros).ejecutar();
		assertEquals("Una silla fea",_tienda.buscar(new IdentificadorDefecto("PV-000001")).getDescripcion());
	}
	
	/**
	 * Test que comprueba que lanza un errorejecucionexception si se intenta cambiar la descripcion sobre un objeto inexistente.
	 */
	@Test (expected=ErrorEjecucionException.class)
	public void testSetDescripcionException() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException{
		_parametros[0]="PV-001003";
		new SetDescripcion(_tienda,_parametros).ejecutar();
		
	}
	
	/**
	 * Test que cambia de ubicacion a un elemento.
	 */
	@Test
	public void testSetCategoria() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException{
		String[] param= new String[4];
		param[0] = "PV-000001";
		param[1] = "sillas";
		param[2] = "feas";
		param[3] = "rotas";
		new SetCategoria(_tienda,param).ejecutar();
		assertEquals(_tienda.buscarPorNombre("rotas"),_tienda.buscar(new IdentificadorDefecto("PV-000001")).getPadre());
	}
	/**
	 * Test que comprueba que se lanza la excepcion si se trata de cambiar de ubicacion un elemento inexistente.
	 */
	@Test (expected=ErrorEjecucionException.class)
	public void testSetCategoriaException() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException{
		String[] param= new String[4];
		param[0] = "PV-000421";
		param[1] = "sillas";
		param[2] = "feas";
		param[3] = "rotas";
		new SetCategoria(_tienda,param).ejecutar();
	}
	
	/**
	 * Test que cambia el precio de un elemento
	 */
	@Test
	public void testSetPrecio() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException{
		_parametros[0]="PV-000001";
		_parametros[1]="20.4";			
		new SetPrecio(_tienda,_parametros).ejecutar();
		assertEquals(20.4f,_tienda.buscar(new IdentificadorDefecto("PV-000001")).getPrecio(),0);
	}
	
	/**
	 * Test que comprueba que salta una exceptcion si se trata de cambiar el precio a un elemento que no esta creado.
	 */
	@Test (expected=ErrorEjecucionException.class)
	public void testSetPrecioException() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException{
		_parametros[0]="PV-000031";
		_parametros[1]="10.2";			
		new SetDescripcion(_tienda,_parametros).ejecutar();
		new SetPrecio(_tienda,_parametros).ejecutar();
	}
	
	/**
	 * Test que cambia a tipo venta un elemento.
	 */
	@Test
	public void testSetTipoVenta() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException{
		_parametros[0]="PS-000001";
		_parametros[1]="venta";			
		new SetTipo(_tienda,_parametros).ejecutar();
		assertNull(_tienda.buscar(new IdentificadorDefecto("PS-000001")));
		assertEquals("silla1",_tienda.buscar(new IdentificadorDefecto("PV-000002")).getNombre());
	}
	
	/**
	 * Test que cambia a tipo subasta un elemento.
	 */
	@Test
	public void testSetTipoSubasta() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException{
		_parametros[0]="PV-000001";
		_parametros[1]="subasta";			
		new SetTipo(_tienda,_parametros).ejecutar();
		assertNull(_tienda.buscar(new IdentificadorDefecto("PV-000001")));
		assertEquals("silla2",_tienda.buscar(new IdentificadorDefecto("PS-000002")).getNombre());
	}
	
	/**
	 * Test que comprueba que se lanza un errorEjecucionException si se trata de cambiar de tipo a un elemento no creado.
	 */
	@Test (expected=ErrorEjecucionException.class)
	public void testSetTipoException() throws ParametrosIncorrectosException,  ErrorEjecucionException, IOException{
		_parametros[0]="PV-000021";		
		new SetDescripcion(_tienda,_parametros).ejecutar();
	}
}
