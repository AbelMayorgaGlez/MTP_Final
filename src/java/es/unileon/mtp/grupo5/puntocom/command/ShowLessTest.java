package es.unileon.mtp.grupo5.puntocom.command;

import static org.junit.Assert.assertEquals;
import java.io.*;
import org.junit.*;
import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.decorator.SalidaSingleton;
import es.unileon.mtp.grupo5.puntocom.excepciones.ParametrosIncorrectosException;
import es.unileon.mtp.grupo5.puntocom.handler.ContadorIdentificadores;
import es.unileon.mtp.grupo5.puntocom.interfaz.InterfazUsuario;
import es.unileon.mtp.grupo5.puntocom.strategyLectura.*;

/**
 * 
 * Test del comando ShowLess.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class ShowLessTest {
	Tienda _tienda;
	@Before
	public void setUp() throws Exception {
		_tienda=new Tienda();
		ContadorIdentificadores.reiniciarContadores();
		SalidaSingleton.getSalida("log/SalidaTest.txt").reiniciarSalida();
		
	}
	/**
	 * Test que comprueba que se muestran todos los elementos con un precio menor al introducido.
	 */
	@Test
	public void testShowless() throws FileNotFoundException, ParametrosIncorrectosException, IOException{
		InterfazUsuario i = new InterfazUsuario(_tienda, new DeArchivo("etc/EntradaShowless.txt"));
		i.run();
		EstrategiaLectura nueva = new DeArchivo("log/SalidaTest.txt");
		for(int j=0;j<=15;j++){
			nueva.sigLinea();	
		}
		assertEquals("silla",nueva.sigLinea());
		assertEquals("silla2",nueva.sigLinea());
		
	}

}
