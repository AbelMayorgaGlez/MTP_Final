package es.unileon.mtp.grupo5.puntocom.strategyIterador;

import es.unileon.mtp.grupo5.puntocom.composite.Elemento;

/**
 * Estrategia concreta del patron Strategy.
 * Valida un Elemento si es una categoria.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class EstrategiaIteradorCategoria implements EstrategiaIteradorComposite {

	@Override
	public boolean esValido(Elemento elem) {
		return (elem.getComposite()!=null);
	}

}
