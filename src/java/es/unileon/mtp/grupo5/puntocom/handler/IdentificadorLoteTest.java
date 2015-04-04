package es.unileon.mtp.grupo5.puntocom.handler;

import static org.junit.Assert.*;

import org.junit.Test;

import es.unileon.mtp.grupo5.puntocom.excepciones.BadFormatException;

/**
 * Clase de prueba del IdentificadorLote.
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public class IdentificadorLoteTest {

	@Test
	public void testIdentificadorLoteStringCorrecto() throws BadFormatException {

		IdentificadorLote id = new IdentificadorLote("LT-5");
		assertEquals("LT-5", id.toString());

	}

	@Test(expected = BadFormatException.class)
	public void testIdentificadorLoteStringIncorrectoLetra()
			throws BadFormatException {
		new IdentificadorLote("LT-a");
	}

	@Test(expected = BadFormatException.class)
	public void testIdentificadorLoteStringIncorrectoNegativo()
			throws BadFormatException {
		new IdentificadorLote("LT--4");
	}

	@Test
	public void testIdentificadorLoteCorrecto() throws BadFormatException {

		IdentificadorLote id1 = new IdentificadorLote("LT-5");
		IdentificadorLote id2 = new IdentificadorLote(id1);
		assertEquals("LT-5", id2.toString());

	}

	@Test(expected = BadFormatException.class)
	public void testIdentificadorLoteIncorrecto() throws BadFormatException {
		new IdentificadorLote(new IdentificadorDefecto("PS-000001"));
	}

	@Test
	public void testCompareTo() throws BadFormatException {

		IdentificadorLote id1 = new IdentificadorLote("LT-5");
		IdentificadorLote id2 = new IdentificadorLote("LT-5");
		assertEquals(0, id1.compareTo(id2));

	}

	@Test
	public void testToString() throws BadFormatException {

		IdentificadorLote id = new IdentificadorLote("LT-5");
		assertEquals("LT-5", id.toString());

	}
}