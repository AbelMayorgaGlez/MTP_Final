package es.unileon.mtp.grupo5.puntocom.command;

import java.io.IOException;

import org.apache.log4j.Logger;
import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.handler.*;
import es.unileon.mtp.grupo5.puntocom.Tienda;

/**
 * 
 * Comando concreto, El usuario puja por un producto.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class Bid implements Comando{
	
	/**
	 * Tienda.
	 */
	private Tienda _tienda;
	
	/**
	 * Precio por el que puja.
	 */
	private float _precio;
	
	/**
	 * Array de strings.
	 */
	private String[] _parametros;
	
	/**
	 * Log de la clase.
	 */
	static Logger _logger = Logger.getLogger(Bid.class);
	
	/**
	 * Constructor.
	 * @param tienda
	 * 				Tienda.
	 * @param parametros
	 * 				Array de Strings que contiene la informacion relativa al producto por el que se quiere pujar.
	 *				En este caso son, por este orden: producto (id o nombre), nombre de usuario, y precio.
	 *
	 * @throws ParametrosIncorrectosException
	 * 				Salta si los parametros no son correctos.
	 */
	public Bid(Tienda tienda, String[] parametros) throws ParametrosIncorrectosException{
		_tienda=tienda;
		_parametros=parametros;
		String msg = "";
		if(_parametros.length!=3){
			msg += "Error de sintaxis. Sintaxis correcta: BID producto, usuario, precio de puja";
			_logger.warn("No se han introducido bien los parametros");
		} else {
			try{
				_precio = Float.parseFloat(_parametros[2]);
			} catch(ArithmeticException e) {
				msg += "Se ha introducido algun caracter no numerico en el precio";
				_logger.warn("No se han introducido bien el precio, hay algun caracter no numerico");
			}
		}		
		if(!msg.equals("")){
			throw new ParametrosIncorrectosException(msg);
		}	
		
	}
	
	@Override
	public void ejecutar() throws ErrorEjecucionException, IOException{
		Elemento elem;
		String msg="";
		Usuario usuario;
		/*
		 * Se busca al elemento.
		 */
		elem = _tienda.buscar(new IdentificadorDefecto(_parametros[0]));
		if(elem == null){		
			elem = _tienda.buscarPorNombre(_parametros[0]);
		}
		if(elem == null){
			msg += "No hay elementos que coincidan con " + _parametros[0];
			_logger.warn("No se ha encontrado ningun elemento que coincida con el nombre o con el identificador "+_parametros[0]);
		} else {
			/*
			 * Busca al usuario, sino lo encuentra lo crea y lo anyade
			 */
			String nombreUsuario = _parametros[1];
			usuario=_tienda.buscarUsuario(nombreUsuario);
			if(usuario == null){
				usuario = new Usuario(nombreUsuario);
				_tienda.anyadirUsuario(usuario);	
				_logger.info("Se ha creado un nuevo usuario de nombre: " + nombreUsuario);
			}
			/*
			 * El usuario puja.
			 */
			usuario.pujar(elem, _precio);
			_logger.info("El usuario " + nombreUsuario + " ha pujado por el elemento " + elem.getNombre() + " con un precio de " + Float.toString(elem.getPrecio()));
		}
		if(!msg.equals("")){
			throw new ErrorEjecucionException(msg);
		}
	}

}