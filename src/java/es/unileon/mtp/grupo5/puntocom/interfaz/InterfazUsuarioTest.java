package es.unileon.mtp.grupo5.puntocom.interfaz;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.decorator.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.*;
import es.unileon.mtp.grupo5.puntocom.strategyLectura.*;
import org.junit.*;

/**
 * Clase de prueba de la InterfazUsuario. Prueba las entradas y salidas por fichero.
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class InterfazUsuarioTest {
	Tienda _tienda;
	
	@Before
	public void setUp() throws Exception {
		ContadorIdentificadores.reiniciarContadores();
		SalidaSingleton.getSalida("log/SalidaTest.txt").reiniciarSalida();
		_tienda=new Tienda();
	}
	
	
	@Test
	public void testFicheroEntrada() throws FileNotFoundException, ParametrosIncorrectosException,  IOException{
		InterfazUsuario i = new InterfazUsuario(_tienda, new DeArchivo("etc/EntradaBien.txt"));
		i.run();
		assertEquals(false,i._tienda.buscarUsuario("Rodrigo").getElementosGanados().isEmpty());
		assertEquals(true,i._tienda.buscarUsuario("Pablo").getElementosGanados().isEmpty());
	}
	
	@Test
	public void testFicheroSalida() throws ParametrosIncorrectosException,  IOException{
		InterfazUsuario i = new InterfazUsuario(_tienda, new DeArchivo("etc/EntradaTestFicheroSalida.txt"));
		i.run();
		EstrategiaLectura nueva = new DeArchivo("log/SalidaTest.txt");
		assertEquals("Producto PS-000001 creado satisfactoriamente",nueva.sigLinea());
		assertEquals("Descripcion: silla",nueva.sigLinea());
		assertEquals("Tipo: subasta",nueva.sigLinea());
		assertEquals("Categoria: Almacen",nueva.sigLinea());
		assertEquals("Subcategorias: ",nueva.sigLinea());
		
	}
	
	@Test
	public void testFicheroEntradaMal() throws ParametrosIncorrectosException,  IOException{
		InterfazUsuario i = new InterfazUsuario(_tienda, new DeArchivo("etc/EntradaMal.txt"));
		i.run();
		EstrategiaLectura nueva = new DeArchivo("log/SalidaTest.txt");
		String str = "Error en el comando. No se ha introducido un comando correcto: Asasasafa sas";
		assertEquals(str,nueva.sigLinea());
	}	
	
}
