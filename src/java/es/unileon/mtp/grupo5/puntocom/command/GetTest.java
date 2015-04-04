package es.unileon.mtp.grupo5.puntocom.command;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.*;
import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.decorator.SalidaSingleton;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.ContadorIdentificadores;
import es.unileon.mtp.grupo5.puntocom.interfaz.InterfazUsuario;
import es.unileon.mtp.grupo5.puntocom.strategyLectura.*;

/**
 * 
 * Test de los comandos Get.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class GetTest {
	Elemento _elem;
	Tienda _tienda;
	String _categoria="";
	String _descripcion="";
	String _nombre="";
	String _precioRebaja="";
	String _precio="";
	
	@Before
	public void setUp() throws Exception {
		_tienda = new Tienda();
		_elem = new ProductoSubasta("silla1","descripcion",10.5f);
		Categoria cat1 = new Categoria("Productos","Productos");
		Categoria cat2 = new Categoria ("Sillas","Sillas");
		_tienda.anyadir(cat1,null);
		_tienda.anyadir(cat2, cat1.getId());
		_tienda.anyadir(_elem,cat2.getId());

		ContadorIdentificadores.reiniciarContadores();
		SalidaSingleton.getSalida("log/SalidaTest.txt").reiniciarSalida();
	}
	
	/**
	 * Test que comprueba que los distintos tipos de Get funcionan correctamente.
	 */
	@Test
	public void testGet() throws ParametrosIncorrectosException, ErrorEjecucionException, IOException{
		InterfazUsuario i = new InterfazUsuario(_tienda, new DeArchivo("etc/EntradaGet.txt"));
		i.run();
		EstrategiaLectura nueva = new DeArchivo("log/SalidaTest.txt");
		assertEquals(nueva.sigLinea(),"La ruta en la que se encuentra el producto es: Almacen-->Productos-->Sillas");
		assertEquals(nueva.sigLinea(),"La descripcion del producto: silla1 es:	descripcion");
		assertEquals(nueva.sigLinea(),"El nombre del producto con el identificador PS-000001 es: silla1");
		assertEquals(nueva.sigLinea(),"El precio del producto silla1 es 10.5");
		assertEquals(nueva.sigLinea(),"El precio del producto silla1 es 9.45");
		assertEquals(nueva.sigLinea(),"Tipo de producto: Producto de subasta");
		
	}

}
