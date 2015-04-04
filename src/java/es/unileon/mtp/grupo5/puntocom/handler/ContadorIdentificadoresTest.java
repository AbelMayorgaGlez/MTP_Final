package es.unileon.mtp.grupo5.puntocom.handler;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Clase de prueba del contador de identificadores.
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class ContadorIdentificadoresTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testReinicioContadores(){
		ContadorIdentificadores.reiniciarContadores();
		assertEquals("1",new IdentificadorCategoria().toString());
		assertEquals("PV-000001", new IdentificadorPV().toString());
		assertEquals("PS-000001", new IdentificadorPS().toString());
		assertEquals("LT-1", new IdentificadorLote().toString());
	}
	
	@Test
	public void testIdentificadoresCategoria(){
		ContadorIdentificadores.reiniciarContadores();
		assertEquals("1",new IdentificadorCategoria().toString());
		assertEquals("2",new IdentificadorCategoria().toString());
		assertEquals("3",new IdentificadorCategoria().toString());
	}
	
	@Test
	public void testIdentificadoresVenta(){
		ContadorIdentificadores.reiniciarContadores();
		assertEquals("PV-000001", new IdentificadorPV().toString());
		assertEquals("PV-000002", new IdentificadorPV().toString());
		assertEquals("PV-000003", new IdentificadorPV().toString());
	}
	
	@Test
	public void testIdentificadoresSubasta(){
		ContadorIdentificadores.reiniciarContadores();
		assertEquals("PS-000001", new IdentificadorPS().toString());
		assertEquals("PS-000002", new IdentificadorPS().toString());
		assertEquals("PS-000003", new IdentificadorPS().toString());
	}

	@Test
	public void testIdentificadoresLote(){
		ContadorIdentificadores.reiniciarContadores();
		assertEquals("LT-1", new IdentificadorLote().toString());
		assertEquals("LT-2", new IdentificadorLote().toString());
		assertEquals("LT-3", new IdentificadorLote().toString());
	}
}
