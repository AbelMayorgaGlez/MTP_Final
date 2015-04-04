package es.unileon.mtp.grupo5.puntocom.strategyLectura;
import java.io.*;

/**
 * Estrategia concreta del patron Strategy.
 * Lee lineas desde un fichero.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class DeArchivo implements EstrategiaLectura {

	/**
	 * Entrada.
	 */
	private BufferedReader _br;
	
	/**
	 * Constructor de clase.
	 * @param nombreFichero Nombre del fichero desde el que leer.
	 * @throws FileNotFoundException Si no se encuentra el fichero.
	 */
	public DeArchivo(String nombreFichero) throws FileNotFoundException{
        _br=new BufferedReader(new FileReader(nombreFichero));   
	}
	
	@Override
	public String sigLinea() throws IOException {
		String linea = "";
		//Mientras se lean lineas en blanco, seguimos leyendo.
		while((linea!=null) && linea.equals("")){
			linea = _br.readLine();
		}
		
		return linea;
		
	}

}
