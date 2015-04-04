package es.unileon.mtp.grupo5.puntocom.strategyLectura;
import java.io.*;

/**
 * Interfaz patron Strategy.
 * Estrategia orientada a la lectura de comandos.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public interface EstrategiaLectura {
	
	/**
	 * Lee una linea de la entrada y la devuelve.
	 * @return Linea leida.
 	 * @throws IOException Si no se puede leer.
	 */
	String sigLinea()throws IOException;
		

}
