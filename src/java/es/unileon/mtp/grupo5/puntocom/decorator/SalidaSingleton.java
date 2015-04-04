package es.unileon.mtp.grupo5.puntocom.decorator;

import java.io.IOException;

/**
 * Patron Singleton para la salida. 
 * Solo permite instanciar una salida, la cual permanecera
 * invariante hasta la finalizacion del programa.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class SalidaSingleton {
	
	/**
	 * Salida.
	 */
	private static SalidaDecorada _salida;
	
	/**
	 * Constructor de clase protegido.
	 */
	protected SalidaSingleton(){}
	
	/**
	 * Instancia una salida simple, por consola.
	 * @return La salida instanciada.
	 */
	public static SalidaDecorada getSalida(){
		if(_salida == null){
			_salida= new Salida();
		}
		return _salida;
	}
	/**
	 * Instancia una salida por fichero.
	 * @param fichero Fichero del que leer.
	 * @return La salida Instanciada.
	 * @throws IOException En caso de que no se pueda acceder al fichero indicado.
	 */
	public static SalidaDecorada getSalida(String fichero) throws IOException{
		if(_salida == null){
			_salida = new AFichero(new Salida(), fichero);
		}
		return _salida;
	}
	
}
