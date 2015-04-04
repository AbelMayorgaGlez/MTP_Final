package es.unileon.mtp.grupo5.puntocom.handler;

import static org.junit.Assert.*;

import org.junit.Test;
import es.unileon.mtp.grupo5.puntocom.excepciones.BadFormatException;

/**
 * Clase de prueba del IdentificadorCategoria.
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class IdentificadorCategoriaTest {

	@Test
	public void testIdentificadorCategoriaStringCorrecto()throws BadFormatException {
		
			IdentificadorCategoria id = new IdentificadorCategoria("5");
			assertEquals("5", id.toString());
		
	}

	@Test(expected = BadFormatException.class)
	public void testIdentificadorCategoriaStringIncorrectoLetra() throws BadFormatException {
		new IdentificadorCategoria("a");
	}

	@Test(expected = BadFormatException.class)
	public void testIdentificadorCategoriaStringIncorrectoNegativo() throws BadFormatException {
		new IdentificadorCategoria("-4");
	}

	@Test
	public void testIdentificadorCategoriaCorrecta()throws BadFormatException {
		
			IdentificadorCategoria id1 = new IdentificadorCategoria("5");
			IdentificadorCategoria id2 = new IdentificadorCategoria(id1);
			assertEquals("5", id2.toString());
		
	}

	@Test(expected = BadFormatException.class)
	public void testIdentificadorCategoriaIncorrecta() throws BadFormatException{
		new IdentificadorCategoria(new IdentificadorDefecto("PS-000001"));
	}

	@Test
	public void testCompareTo() throws BadFormatException{
		
			IdentificadorCategoria id1 = new IdentificadorCategoria("5");
			IdentificadorCategoria id2 = new IdentificadorCategoria("5");
			assertEquals(0, id1.compareTo(id2));
		
	}

	@Test
	public void testToString() throws BadFormatException{
		
			IdentificadorCategoria id = new IdentificadorCategoria("5");
			assertEquals("5", id.toString());
		
	}
}
