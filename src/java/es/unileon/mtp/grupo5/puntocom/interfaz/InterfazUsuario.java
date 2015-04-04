package es.unileon.mtp.grupo5.puntocom.interfaz;

import java.io.IOException;

import org.apache.log4j.Logger;

import es.unileon.mtp.grupo5.puntocom.strategyLectura.*;
import es.unileon.mtp.grupo5.puntocom.command.*;
import es.unileon.mtp.grupo5.puntocom.*;
import es.unileon.mtp.grupo5.puntocom.decorator.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
/**
 * 
 * Cliente del command, crea los comandos concretos.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class InterfazUsuario {
	/**
	 *Estrategia de donde se leera ( de consola o de fichero )
	 */
	private EstrategiaLectura _estrategia;

	
	Tienda _tienda;
	/**
	 * logger de la clase.
	 */
	static Logger _logger = Logger.getLogger(InterfazUsuario.class);
	
	/**
	 * Constructor.
	 * @param tienda 
	 * 				Tienda sobre la que aplicar la interfaz
	 * @param entrada 
	 * 				Lectura de los comandos.
	 */
	public InterfazUsuario(Tienda tienda, EstrategiaLectura entrada){
		_estrategia = entrada;
		_tienda = tienda;
	}
	/**
	 * Metodo que leera una linea, identificara que comando es y lo creara.
	 * @param linea
	 * 			linea que contiene la informacion de que comando es.
	 * @return El comando creado.
	 * @throws ParametrosIncorrectosException Si la linea no se corresponde con ningun comando.
	 */
	public Comando crearComando(String linea) throws ParametrosIncorrectosException{
		
		Comando c = null;
		int blanco = linea.indexOf(" ");
		/*
		 * Si no hay un caracter en blanco, el comando es una palabra simple, sin argumentos.
		 */
		if (blanco == -1) {
			if(linea.equalsIgnoreCase("SHOWGROUPS")){
				c = new ShowGroups(_tienda);
				_logger.info("Comando identificado como showgroups");
			}
		} else {
			String primero = linea.substring(0, linea.indexOf(" "));
			String resto = linea.substring(linea.indexOf(" ") + 1);
			primero = primero.toUpperCase();
			String[] array = resto.split(", ");

			if (primero.equals("NEWPROD")) {
				c = new NewProd(_tienda, array);
				_logger.info("Comando identificado como newprod");
			} else if (primero.equals("SHOWLESS")){
				c = new ShowLess(_tienda, array);
				_logger.info("Comando identificado como showless");
			} else if (primero.equals("SELL")) {
				c = new Sell(_tienda, array);
				_logger.info("Comando identificado como sell");
			} else if (primero.equals("AUCTION")) {
				c = new Auction(_tienda, array);
				_logger.info("Comando identificado como auction");
			} else if (primero.equals("BID")) {
				c = new Bid(_tienda, array);
				_logger.info("Comando identificado como bid");
			} else if (primero.equals("ENDAUCTION")) {
				c = new EndAuction(_tienda, array);
				_logger.info("Comando identificado como endauction");
			} else if (primero.equals("SET")) {
				/*
				 * Todos los sets reciben argumentos, por lo que nos aseguramos primero.
				 */
				if(resto.indexOf(" ")!=-1){
					primero = resto.substring(0, resto.indexOf(" ")).toUpperCase();
					resto = resto.substring(resto.indexOf(" ") + 1);
					String[] arrayResto = resto.split(", ");
	
					if (primero.equals("CATEGORIA")) {
						c = new SetCategoria(_tienda, arrayResto);
						_logger.info("Comando identificado como SetCategoria");
					} else if (primero.equals("DESCRIPCION")) {
						c = new SetDescripcion(_tienda, arrayResto);
						_logger.info("Comando identificado como SetDescripcion");
					} else if (primero.equals("NOMBRE")) {
						c = new SetNombre(_tienda, arrayResto);
						_logger.info("Comando identificado como SetNombre");
					} else if (primero.equals("PRECIO")) {
						c = new SetPrecio(_tienda, arrayResto);
						_logger.info("Comando identificador como SetPrecio");
					} else if (primero.equals("TIPO")) {
						c = new SetTipo(_tienda, arrayResto);
						_logger.info("Comando identificado como SetTipo");
					}
				}
			} else if (primero.equals("GET")) {
				/*
				 * Todos los gets reciben argumentos, por lo que nos aseguramos primero.
				 */
				if(resto.indexOf(" ")!=-1){
					primero = resto.substring(0, resto.indexOf(" ")).toUpperCase();
					resto = resto.substring(resto.indexOf(" ") + 1);
					String[] arrayResto = resto.split(", ");
	
					if (primero.equals("DESCRIPCION")) {
						c = new GetDescripcion(_tienda, arrayResto);
						_logger.info("Comando identificado como GetDescripcion");
					} else if (primero.equals("CATEGORIA")) {
						c = new GetCategoria(_tienda, arrayResto);
						_logger.info("Comando identificado como GetCategoria");
					} else if (primero.equals("NOMBRE")) {
						c = new GetNombre(_tienda, arrayResto);
						_logger.info("Comando identificado como GetNombre");
					} else if (primero.equals("PRECIO")) {
						c = new GetPrecio(_tienda, arrayResto);
						_logger.info("Comando identificado como GetPrecio");
					} else if (primero.equals("TIPO")) {
						c = new GetTipo(_tienda, arrayResto);
						_logger.info("Comando identificado como GetTipo");
					}
				}
			}

		}
		if (c == null) {
			_logger.warn("No se ha introducido un comando correcto");
			String msg = "No se ha introducido un comando correcto: " + linea;
			throw new ParametrosIncorrectosException(msg);
		}
		return c;
	}

	public void run() throws IOException{
		try{
			String linea = null;
			while ((linea = _estrategia.sigLinea()) != null) {
				try {
					crearComando(linea).ejecutar();
					_logger.info("Comando creado y ejecutado con exito");
				} catch (ParametrosIncorrectosException e){
					_logger.warn("Error en la creacion del comando. " + e.getMessage());
					SalidaSingleton.getSalida().escribir("Error en el comando. " + e.getMessage());
				} catch (ErrorEjecucionException e) {
					_logger.warn("Fallo en la ejecucion del comando: " + e.getMessage());
					SalidaSingleton.getSalida().escribir(e.getMessage());
				}
			}
		} catch (IOException e){
			_logger.error("Se ha producido un error de entrada/salida. Se cerrara el programa");
		}
	}

}
