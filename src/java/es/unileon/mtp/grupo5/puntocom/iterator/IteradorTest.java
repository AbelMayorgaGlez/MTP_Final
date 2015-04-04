package es.unileon.mtp.grupo5.puntocom.iterator;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.composite.Categoria;
import es.unileon.mtp.grupo5.puntocom.composite.Elemento;
import es.unileon.mtp.grupo5.puntocom.composite.ProductoSubasta;
import es.unileon.mtp.grupo5.puntocom.composite.ProductoVenta;
import es.unileon.mtp.grupo5.puntocom.excepciones.BadFormatException;
import es.unileon.mtp.grupo5.puntocom.excepciones.OperacionNoPermitidaException;
import es.unileon.mtp.grupo5.puntocom.excepciones.ParametrosIncorrectosException;
import es.unileon.mtp.grupo5.puntocom.handler.ContadorIdentificadores;
import es.unileon.mtp.grupo5.puntocom.handler.IdentificadorDefecto;

/**
 * Clase de prueba del Iterador.
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class IteradorTest {
	
	Elemento _raiz, _cat1, _cat2, _cat3, _cat4, _pr1, _pr2, _pr3;
	
	@Before
	public void setUp() throws Exception {
		ContadorIdentificadores.reiniciarContadores();
		_raiz = new Categoria("Almacen","Almacen");
		_cat1 = new Categoria("Mesas", "Mesas");
		_cat2 = new Categoria("Sillas","Sillas");
		_cat3 = new Categoria("Encimeras", "Encimeras");
		_cat4 = new Categoria("Taburetes", "Taburetes");
		_pr1 = new ProductoVenta(new IdentificadorDefecto("PV-000001"), "Mesa1",
				"Mesa con bonitas flores", 30.5f);
		_pr2 = new ProductoSubasta(new IdentificadorDefecto("PS-000001"), "Silla1",
				"Silla con 3 patas", 10.5f);
		_pr3 = new ProductoVenta(new IdentificadorDefecto("PV-000002"), "Mesa2",
				"Mesa de camping", 30.2f);
		_raiz.getComposite().anyadir(_cat1);
		_raiz.getComposite().anyadir(_cat2);
		_cat1.getComposite().anyadir(_pr1);
		_cat1.getComposite().anyadir(_pr3);
		_cat1.getComposite().anyadir(_cat3);
		_cat2.getComposite().anyadir(_pr2);
		_cat2.getComposite().anyadir(_cat4);
	}

	@Test
	public void testIteradorCategoria() throws OperacionNoPermitidaException, ParametrosIncorrectosException{
		Iterador<Elemento> it = _raiz.darIterador("categorias");
		assertEquals(_cat1,it.siguienteElemento());
		assertEquals(_cat3,it.siguienteElemento());
		assertEquals(_cat2,it.siguienteElemento());
		assertEquals(_cat4,it.siguienteElemento());
		assertEquals(false,it.haySiguiente());
	}
	
	@Test
	public void testIteradorPrecio(){
		Iterador<Elemento> it = _raiz.darIterador("30.4");
		assertEquals(_pr3,it.siguienteElemento());
		assertEquals(_pr2,it.siguienteElemento());
		assertEquals(false,it.haySiguiente());
	}
	
	@Test
	public void testIteradorVacio() throws ParametrosIncorrectosException{
		Tienda tienda = new Tienda();
		Iterador<Elemento> itr = tienda.darIterador("categoria");
		assertFalse(itr.haySiguiente());
	}
	
	@Test
	public void testIteradorSinCategoria() throws ParametrosIncorrectosException, BadFormatException{
		Tienda tienda = new Tienda();
		tienda.anyadir(new ProductoVenta(new IdentificadorDefecto("PV-000001"),"pr1", "pr1", 10.5f), null);
		tienda.anyadir(new ProductoVenta(new IdentificadorDefecto("PV-000002"),"pr2", "pr2", 10.5f), null);
		Iterador<Elemento> itr = tienda.darIterador("categoria");
		assertFalse(itr.haySiguiente());
	}
	
	@Test
	public void testIteradorSinPrecioMenor(){
		Iterador<Elemento> itr = _raiz.darIterador("1");
		assertFalse(itr.haySiguiente());
	}
	
}
