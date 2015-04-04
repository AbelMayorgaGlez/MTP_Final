package es.unileon.mtp.grupo5.puntocom.strategyPrecio;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.ParametrosIncorrectosException;
import es.unileon.mtp.grupo5.puntocom.handler.ContadorIdentificadores;

/**
 * Clase de prueba del Strategy para los precios.
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class EstrategiaPrecioTest {
	
	Elemento _elem;
	@Before
	public void setUp() throws Exception {
		ContadorIdentificadores.reiniciarContadores();
		_elem = new ProductoVenta("Silla", "Una silla",10.5f);
	}
	
	@Test
	public void testPrecioPorDefecto(){
		assertEquals(10.5f,_elem.getPrecio(), 0);
	}
	
	@Test
	public void testPrecioRebajado() throws ParametrosIncorrectosException{
		_elem.setEstrategia(new Rebaja(0.5f));
		assertEquals(5.25f,_elem.getPrecio(),0);
	}

}
