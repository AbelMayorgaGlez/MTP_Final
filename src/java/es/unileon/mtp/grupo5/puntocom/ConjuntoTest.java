package es.unileon.mtp.grupo5.puntocom;

import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import es.unileon.mtp.grupo5.puntocom.command.*;
import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.handler.*;
import es.unileon.mtp.grupo5.puntocom.interfaz.*;
import es.unileon.mtp.grupo5.puntocom.iterator.*;
import es.unileon.mtp.grupo5.puntocom.observer.*;
import es.unileon.mtp.grupo5.puntocom.strategyPrecio.*;


/**
 * 
 * Suite de prueba de todos los tests.
 * 
 * @author Mario Carracedo Garcia.
 * @author Alberto Guzman Goyanes.
 * @author Hector Rodriguez Gutierrez.
 * @author Abel Mayorga Gonzalez.
 * @author Pablo Lobato Gonzalez
 * @author Rodrigo Urdiales Santos
 * @version 1.0
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({InterfazUsuarioTest.class, TodosComandoTest.class, IteradorTest.class, ObservadorSubastaTest.class,EstrategiaPrecioTest.class, LoteTest.class, TiendaTest.class,CompositeTest.class,TodosIdentificadorTest.class})
public class ConjuntoTest {

	@BeforeClass
	public static void startup(){
		PropertyConfigurator.configure("etc/log4j.conf");
	}
}
