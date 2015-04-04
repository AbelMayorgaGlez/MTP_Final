package es.unileon.mtp.grupo5.puntocom.iterator;

import java.util.Stack;
import es.unileon.mtp.grupo5.puntocom.composite.*;
import es.unileon.mtp.grupo5.puntocom.strategyIterador.EstrategiaIteradorComposite;

/**
 * Iterador concreto. Itera sobre un composite siguiendo una estrategia concreta.
 * 
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class IteradorComposite implements Iterador<Elemento> {

	/**
	 * Elemento sobre el que se itera.
	 */
	private Elemento _elem;
	/**
	 * Subiterador para los hijos del elemento.
	 */
	private Iterador<Elemento> _subIterador;
	/**
	 * Pila para guardar los hijos.
	 */
	private Stack<Elemento> _pila;
	/**
	 * Hijo del elemento sobre el que se esta iterando.
	 */
	private Elemento _actual;
	/**
	 * Estrategia que decide que elementos se devuelven y cuales no.
	 */
	private EstrategiaIteradorComposite _estrategia;

	/**
	 * Constructor de clase.
	 * @param elem Elemento sobre el que iterar.
	 * @param estrategia Estrategia de iteracion a seguir.
	 */
	public IteradorComposite(Elemento elem, EstrategiaIteradorComposite estrategia){
		_estrategia = estrategia;
		_elem = elem;
		_actual = null;
		_subIterador = null;
		regenerarPila();
	}
	
	/**
	 * Restablece la pila al estado inicial.
	 * Apila en ella todos los hijos del elemento sobre el que se itera.
	 */
	private void regenerarPila(){
		_pila = new Stack<Elemento>();
		for(int i = _elem.darNumHijos()-1; i >= 0; i--){
			_pila.push(_elem.darHijo(i));
		}
	}
	
	@Override
	public Elemento primerElemento() {
		//Restaura el iterador a su estado inicial.
		_actual = null;
		_subIterador = null;
		regenerarPila();
		//Luego devuelve el siguiente elemento, el cual sera el primero.
		return siguienteElemento();
	}

	@Override
	public Elemento siguienteElemento() {
		if(!haySiguiente()){
			return null;
		} else {
			/*
			 * Recordar que haySiguiente() avanza al siguiente si lo hay,
			 * por eso se devuelve el actual.
			 */
			return elementoActual();
		}
	}

	@Override
	public boolean haySiguiente() {
		boolean hay = true;
		// Si se ha vaciado la pila, entonces ya no se puede iterar mas.
		if(_pila.isEmpty()){
			hay = false;
		// Si _actual es null, tenemos que comenzar la iteracion por un nuevo hijo
		} else if(_actual == null){
			//lo cogemos de la pila
			_actual = _pila.peek();
			//Si la estrategia lo valida, entonces este es el siguiente.
			if(_estrategia.esValido(_actual)){
				hay = true;
			//Si no, hacemos una llamada recursiva
			} else {
				hay = haySiguiente();
			}
		// Si ya hemos considerado _actual, el siguiente paso es iterar sobre el.
		} else if(_subIterador == null){
			//Obtenemos un iterador sobre el con la misma estrategia.
			_subIterador = _actual.darIterador(_estrategia);
			/*
			 * Y llamamos recursivamente a haySiguiente() para comprobar si
			 * por este nuevo iterador encontramos algun elemento valido.
			 */
			hay = haySiguiente();
		// Si ell subiterador encuentra elementos validos
		} else if(_subIterador.haySiguiente()){
			hay = true;
		/* 
		 * Pero si no los encuentra, es porque hemos terminado de iterar
		 * sobre el elemento actual.
		 */
		} else {
			//Restablecemos los atributos a null.
			_subIterador = null;
			_actual = null;
			/*
			 *  Y sacamos el elemento de la pila, de forma que ya 
			 *  se ha terminado de iterar totalmente por el
			 */
			_pila.pop();
			// Hacemos llamada recursiva para ver si otro elemento que dar.
			hay = haySiguiente();
			/*
			 * El truco consiste en que cuando se intenta iterar sobre un Producto, se devuelve
			 * un IteradorNulo, y acto seguido se quita de la pila y actual y subiterador se ponen
			 * a null 
			 */
		}
		return hay;
	}

	@Override
	public Elemento elementoActual() {
		// Si _actual es null, tenemos que considerar un nuevo hijo.
		if(_actual == null){
			return siguienteElemento();
		// Si no, si el subiterador es null, entonces el actual es _actual.
		}else if(_subIterador == null){
			return _actual;
		// Si el subiterador no es null, entonces el actual lo tendra el.
		} else {
			return _subIterador.elementoActual();
		}
	}


}
