package es.unileon.mtp.grupo5.puntocom.handler;

import static org.junit.Assert.*;

import org.junit.Test;

import es.unileon.mtp.grupo5.puntocom.excepciones.BadFormatException;

/**
 * Clase de prueba del IteradorPV.
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class IdentificadorPVTest {
	
	@Test
	public void testIdentificadorPVStringCorrecto() throws BadFormatException{
		new IdentificadorPV("PV-000001");
	}
	
	@Test(expected = BadFormatException.class)
	public void testIdentificadorPVStringIncorrectoLongitud() throws BadFormatException {
			new IdentificadorPV("PV-00001");
	}
	
	@Test(expected = BadFormatException.class)
	public void testIdentificadorPVStringIncorrectoNoPV() throws BadFormatException {
			new IdentificadorPV("PT-000001");
		}
 
	@Test(expected = BadFormatException.class)
	public void testIdentificadorPVStringIncorrectoIgualCero() throws BadFormatException {
				new IdentificadorPV("PV-000000");
		}
	
	@Test(expected = BadFormatException.class)
	public void testIdentificadorPVStringIncorrectoSeisDigitos() throws BadFormatException {
				new IdentificadorPV("PV-aaaaaa");
		}
 

	@Test(expected = BadFormatException.class)
	public void testIdentificadorPVIdentificador() throws BadFormatException {
		new IdentificadorPV(new IdentificadorDefecto("PS-000001"));
	}

	@Test
	public void testCompareTo() throws BadFormatException{
		IdentificadorPV id1 = new IdentificadorPV("PV-000001");
		IdentificadorPV id2 = new IdentificadorPV("PV-000001");
		assertEquals(0, id1.compareTo(id2));
	}

	@Test
	public void testToString() throws BadFormatException{
		IdentificadorPV id = new IdentificadorPV("PV-000001");
		assertEquals("PV-000001", id.toString());
	}
	
}