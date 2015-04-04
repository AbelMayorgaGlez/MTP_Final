package es.unileon.mtp.grupo5.puntocom.observer;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import es.unileon.mtp.grupo5.puntocom.*;
import es.unileon.mtp.grupo5.puntocom.composite.Categoria;
import es.unileon.mtp.grupo5.puntocom.composite.Elemento;
import es.unileon.mtp.grupo5.puntocom.composite.ProductoSubasta;
import es.unileon.mtp.grupo5.puntocom.composite.ProductoVenta;
import es.unileon.mtp.grupo5.puntocom.excepciones.OperacionNoPermitidaException;
import es.unileon.mtp.grupo5.puntocom.handler.ContadorIdentificadores;
import es.unileon.mtp.grupo5.puntocom.handler.IdentificadorDefecto;

import org.apache.log4j.Logger;

/**
 * Clase de prueba del ObservadorSubasta.
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class ObservadorSubastaTest {
	Elemento _raiz, _cat1, _cat2, _pr1, _pr2, _pr3;
	Usuario _usr1,_usr2,_usr3;
	Tienda _tienda;
	static Logger _logger = Logger.getLogger(ObservadorSubastaTest.class);
	@Before
	public void setUp() throws Exception {
		ContadorIdentificadores.reiniciarContadores();
		_tienda = new Tienda();
		_usr1 = new Usuario("Pepito");
		_usr2 = new Usuario("Menganito");
		_usr3 = new Usuario("Fulano de TALF");
		_tienda.anyadirUsuario(_usr1);
		_tienda.anyadirUsuario(_usr2);
		_raiz = new Categoria("Almacen","Almacen");
		_cat1 = new Categoria("Mesas", "Mesas");
		_cat2 = new Categoria("Sillas","Sillas");
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
		_cat2.getComposite().anyadir(_pr2);
		_pr2.comenzarSubasta();
		_usr3.seguirNuevaSubasta(_pr2);
		_usr2.seguirNuevaSubasta(_pr2);
		_usr1.seguirNuevaSubasta(_pr2);
		_usr2.pujar(_pr2,13.7f);
	}
	
	@Test
	public void usuarioPujaProducto() throws OperacionNoPermitidaException{
		_logger.warn("Entrando Test1");
		assertEquals(false,_usr2.getElementosSobrePujados().contains(_pr2));
		assertEquals(true,_usr1.getElementosSobrePujados().contains(_pr2));
		assertEquals(true,_usr3.getElementosSobrePujados().contains(_pr2));
	}
	
	@Test
	public void finalizarPujaBorrarObservadores() throws OperacionNoPermitidaException{
		_logger.warn("Entrando Test2");
		_pr2.finalizarSubasta();
		assertEquals(true,_usr2.getElementosGanados().contains(_pr2));
		assertEquals(true,_usr1.getElementosGanados().isEmpty());
		assertEquals(true,_usr3.getElementosGanados().isEmpty());
		assertEquals(false,_usr1.hayObservador());
		assertEquals(false,_usr3.hayObservador());
		assertEquals(false,_usr2.hayObservador());
	}

	

}
