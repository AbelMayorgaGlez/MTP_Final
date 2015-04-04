package es.unileon.mtp.grupo5.puntocom.command;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.unileon.mtp.grupo5.puntocom.*;
import es.unileon.mtp.grupo5.puntocom.decorator.SalidaSingleton;
import es.unileon.mtp.grupo5.puntocom.excepciones.ParametrosIncorrectosException;
import es.unileon.mtp.grupo5.puntocom.handler.ContadorIdentificadores;
import es.unileon.mtp.grupo5.puntocom.interfaz.InterfazUsuario;
import es.unileon.mtp.grupo5.puntocom.strategyLectura.*;

/**
 * 
 * Test del comando ShowGroups.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class ShowGroupsTest {
	Tienda _tienda;
	@Before
	public void setUp() throws Exception {
		_tienda=new Tienda();
		ContadorIdentificadores.reiniciarContadores();
		SalidaSingleton.getSalida("log/SalidaTest.txt").reiniciarSalida();
	}
	
	/**
	 * Test que comprueba que se listan todas las categorias existentes
	 */
	@Test
	public void testShowGroups() throws FileNotFoundException, ParametrosIncorrectosException, IOException{
		InterfazUsuario i = new InterfazUsuario(_tienda, new DeArchivo("etc/EntradaShowGroups.txt"));
		i.run();
		EstrategiaLectura nueva = new DeArchivo("log/SalidaTest.txt");	
		for(int j=0;j<=10;j++){
			nueva.sigLinea();
		}
		assertEquals("Antiguedades",nueva.sigLinea());
		assertEquals("Sillas",nueva.sigLinea());
		assertEquals("Mesas",nueva.sigLinea());
		assertEquals("Mesas de campo",nueva.sigLinea());
		
	}


}
