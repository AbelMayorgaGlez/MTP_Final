package es.unileon.mtp.grupo5.puntocom.handler;

import static org.junit.Assert.*;

import org.junit.Test;

import es.unileon.mtp.grupo5.puntocom.excepciones.BadFormatException;

/**
 * Clase de prueba del IdentificadorPS.
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class IdentificadorPSTest {

	@Test
	public void testIdentificadorPSStringCorrecto() throws BadFormatException{
		new IdentificadorPS("PS-000001");
	}
	
	@Test(expected = BadFormatException.class)
	public void testIdentificadorPSStringIncorrectoLongitud() throws BadFormatException {
			new IdentificadorPS("PS-00001");
	}
	
	@Test(expected = BadFormatException.class)
	public void testIdentificadorPSStringIncorrectoNoPS() throws BadFormatException {
				new IdentificadorPS("PT-000001");
		}
 
	@Test(expected = BadFormatException.class)
	public void testIdentificadorPSStringIncorrectoIgualCero() throws BadFormatException {
				new IdentificadorPS("PS-000000");
		}
	
	@Test(expected = BadFormatException.class)
	public void testIdentificadorPSStringIncorrectoSeisDigitos() throws BadFormatException {
				new IdentificadorPS("PS-aaaaaa");
		}
 

	@Test(expected = BadFormatException.class)
	public void testIdentificadorPSIdentificador() throws BadFormatException{
			new IdentificadorPS(new IdentificadorDefecto("PV-000001"));
		}

	@Test
	public void testCompareTo() throws BadFormatException{
		IdentificadorPS id1 = new IdentificadorPS("PS-000001");
		IdentificadorPS id2 = new IdentificadorPS("PS-000001");
		assertEquals(0, id1.compareTo(id2));
	}

	@Test
	public void testToString() throws BadFormatException{
		IdentificadorPS id = new IdentificadorPS("PS-000001");
		assertEquals("PS-000001", id.toString());
	}
}
