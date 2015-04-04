package es.unileon.mtp.grupo5.puntocom.command;

import es.unileon.mtp.grupo5.puntocom.excepciones.*;
import java.io.IOException;

/**
 * 
 * Command, declara la interfaz para la ejecucion de operaciones.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public interface Comando{
	
	void ejecutar() throws ErrorEjecucionException, IOException;

}
