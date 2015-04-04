package es.unileon.mtp.grupo5.puntocom.command;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * 
 * Suite de prueba de todos los tests de los comandos.
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
/**
 * Suite que reune todos los Test de los comandos concretos.
 */
@Suite.SuiteClasses({AuctionTest.class, BidTest.class, EndAuctionTest.class,GetTest.class, NewProdTest.class,SellTest.class,SetTest.class,ShowGroupsTest.class,ShowLessTest.class})
public class TodosComandoTest {

}
