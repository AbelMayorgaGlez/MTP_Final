package es.unileon.mtp.grupo5.puntocom.command;

import java.io.IOException;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import es.unileon.mtp.grupo5.puntocom.decorator.*;
import es.unileon.mtp.grupo5.puntocom.composite.*;
import java.util.*;
import es.unileon.mtp.grupo5.puntocom.Tienda;
import org.apache.log4j.Logger;

/**
 * 
 * Comando concreto. Crea un producto.
 * 
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class NewProd implements Comando{
	/**
	 * Precio.
	 */
	private float _precio;
	/**
	 * Tienda.
	 */
	private Tienda _tienda;
	
	/**
	 * Array de Strings.
	 */
	private String[] _parametros;
	
	/**
	 * Log de la clase.
	 */
	static Logger _logger = Logger.getLogger(NewProd.class);
	/**
	 * Constructor		
	 * @param tienda
	 * 				Tienda.
	 * @param parametros
	 * 				Array de Strings que contiene la informacion necesaria para crear el producto.
	 * 				En este caso y por este orden son: nombre, descripcion, precio, tipo, categoria y [subcategorias].
	 * 				En caso de que el producto se quiera anyadir directamente en la raiz de la tienda,
	 * 				fuera de cualquier categoria, entonces el campo Categoria debe contener "Almacen" y dejar las subcategorias vacias.
	 * @throws ParametrosIncorrectosException
	 * 				Se lanza si hay parametros incorrectos.
	 */
	public NewProd(Tienda tienda, String[] parametros)throws ParametrosIncorrectosException{
		_tienda=tienda;
		_parametros=parametros;
		String msg = "";
		if(_parametros.length < 5){
			msg += "Error de sintaxis. Sintaxis correcta: NEWPROD nombre, descripcion, precio, tipo, categoria[, subcategorias...].";
		} else {
			try{
				_precio = Float.parseFloat(parametros[2]);
			} catch (NumberFormatException e){
				msg += "El campo precio debe ser numerico";
			}
		}			
		if(!msg.equals("")){
			_logger.warn("No se han introducido los parametros correctamente.");
			throw new ParametrosIncorrectosException(msg);
		}		
	}
	
	@Override
	public void ejecutar() throws ErrorEjecucionException, IOException{
		String msg = "";
		String[] categorias = Arrays.copyOfRange(_parametros, 4, _parametros.length);	
		Elemento pr = null;
		try{	
			/*
			 * Vemos si es un producto de venta o de subasta y lo creamos
			 */
			if(_parametros[3].equalsIgnoreCase("SUBASTA")){
				pr = new ProductoSubasta(_parametros[0],_parametros[1],_precio);
			} else if(_parametros[3].equalsIgnoreCase("VENTA")){
				pr = new ProductoVenta(_parametros[0],_parametros[1],_precio);
			} else {
				msg += "El identificador no se corresponde con ningun tipo de producto";
			}
			/*
			 * Si no es null se anyade a las categorias indicadas
			 */
			if(pr != null){
				_tienda.anyadir(pr,_tienda.crearCategorias(categorias).getId());
				_logger.info("Producto: " + pr.getNombre() + " creado con exito");
				
				String subcat="";
				if(categorias.length>=1){
					for(int i=1;i<categorias.length;i++){
						subcat+=categorias[i]+" ";
					}
				}
				SalidaSingleton.getSalida().escribir("Producto " + pr.getId().toString() + " creado satisfactoriamente");
				SalidaSingleton.getSalida().escribir("Descripcion: " + _parametros[1]);
				SalidaSingleton.getSalida().escribir("Tipo: " + _parametros[3]);
				SalidaSingleton.getSalida().escribir("Categoria: " + categorias[0]);
				SalidaSingleton.getSalida().escribir("Subcategorias: " + subcat);
				_logger.info("Producto: "+_parametros[0]+" creado y almacenado en las categorias con exito");
			}
		} catch (ParametrosIncorrectosException e){
			_logger.warn("Error en la creacion y colocacion del producto. " + e.getMessage());
			msg += e.getMessage();
		}
		if(!msg.equals("")){
			throw new ErrorEjecucionException(msg);
		}
		
	}
}
