package es.unileon.mtp.grupo5.puntocom.handler;

/** 
 * 
 * Interfaz del patron Handler. Define las operaciones comunes a los identificadores concretos.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia 
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */

public interface Identificador {
	
	/**
	 * Devuelve un String que representa al identificador.
	 * @return String que representa al identificador.
	 */
	public String toString();
	
	/**
	 * Compara el identificador actual con otro identificador.
	 * @param otro Identificador con el que se quiere comparar.
	 * @return 0 si son iguales, en otro caso cualquier otro numero.
	 */
	public int compareTo(Identificador otro);
}
