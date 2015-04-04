package es.unileon.mtp.grupo5.puntocom.command;
import java.io.IOException;
import java.util.*;
import es.unileon.mtp.grupo5.puntocom.Tienda;
import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.decorator.*;
import es.unileon.mtp.grupo5.puntocom.excepciones.*;

/**
 * 
 * Comando concreto. Da la ruta de categorias en la que esta contenida el elemento.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class GetCategoria extends Get {
	/**
	 * Constructor.
	 * @param tienda
	 * 				Tienda.
	 * @param parametros
	 * 				Array de Strings que contiene la informacion relativa al producto.
	 * 				En este caso solo hay un parametros que es el producto del que obtener las categorias.
	 * @throws ParametrosIncorrectosException
	 * 				Se lanza si hay parametros incorrectos.
	 */
	public GetCategoria(Tienda tienda, String[] parametros) throws ParametrosIncorrectosException {
		super(tienda, parametros);
		String msg = "";
		if(parametros.length!=1){
			msg += "Error de sintaxis. Sintaxis correcta: GET CATEGORIA producto";
			_logger.info("No se han introducido bien los parametros");
		}
		if(!msg.equals("")){
			throw new ParametrosIncorrectosException(msg);
		}
	}

	@Override
	public void ejecutar() throws ErrorEjecucionException, IOException{
		//Llamamos al ejecutar de la clase Get para que salten las excepciones en caso de tengan que saltar.
		super.ejecutar();
		List<Elemento> lista = new ArrayList<Elemento>();
		Elemento ant = _elemento;
		/*
		 * Guardo en lista todas las categorias ascendentes padre de producto.
		 */
		while((ant = ant.getPadre())!=null){
			lista.add(ant);
		}
		/*
		 * En ruta guardo el nombre de la categoria raiz.
		 */
		String ruta = lista.get(lista.size()-1).getNombre();
		/*
		 * Con el bucle guardamos desde la siguiente categoria a raiz el nombre de todas las categorias hasta llegar al final
		 */
		for(int i = lista.size()-2; i >= 0; i--){
			ruta += "-->"+lista.get(i).getNombre();
		}
		SalidaSingleton.getSalida().escribir("La ruta en la que se encuentra el producto es: " + ruta);

	}

}
