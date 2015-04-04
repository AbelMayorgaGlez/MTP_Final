package es.unileon.mtp.grupo5.puntocom.strategyLectura;
import java.io.*;

/**
 * Estrategia concreta del patron Strategy.
 * Lee lineas desde la consola de comandos.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class DeConsola implements EstrategiaLectura {

	/**
	 * Consola del sistema.
	 */
	private Console _cn = System.console();
	
	@Override
	public String sigLinea() throws IOException {
		String linea = "";
		//Mientras se lean lineas vacias, seguimos leyendo
		while((linea!=null) && linea.equals("")){
			linea = _cn.readLine();
		}

		return linea;
	}

}
