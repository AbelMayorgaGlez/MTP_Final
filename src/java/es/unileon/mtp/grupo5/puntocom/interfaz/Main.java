package es.unileon.mtp.grupo5.puntocom.interfaz;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.strategyLectura.*;
import es.unileon.mtp.grupo5.puntocom.decorator.*;

/**
 * 
 * Clase Main que arranca el programa
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class Main {
	/**
	 * logger de la clase.
	 */
	static Logger _logger = Logger.getLogger(Main.class);
	
	/**
	 * Metodo main que inicia el programa
	 * @param args Argumentos pasados por la consola.
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure("etc/log4j.conf");
		EstrategiaLectura en = null;
		try {
			/*
			 * Dependiendo del numero de argumentos, cogemos una salida u otra
			 */
			if(args.length >= 2){
				SalidaSingleton.getSalida(args[1]);
			} else {
				SalidaSingleton.getSalida();
			}
			/*
			 * Dependiendo del numero de argumentos, cogemos una entrada u otra
			 */
			if (args.length==0){
				en = new DeConsola();
			} else {
				en = new DeArchivo(args[0]);
			}
			new InterfazUsuario(new Tienda(),en).run();
		} catch (FileNotFoundException e) {
			_logger.warn("No se ha encontrado el fichero especificado");
		} catch (IOException e){
			_logger.warn("No se ha podido escribir");
		}
	}

}
