package es.unileon.mtp.grupo5.puntocom.handler;

/**
 * Clase Singleton que funciona de contador para los identificadores.
 * @author Abel Mayorga Gonzalez
 * @author Hector Rodriguez Gutierrez
 * @author Alberto Guzman Goyanes
 * @author Mario Carracedo Garcia
 * @author Rodrigo Urdiales Santos
 * @author Pablo Lobato Gonzalez
 * @version 1.0
 */
public class ContadorIdentificadores {

	/**
	 * Contador de IdentificadorCategoria;
	 */
	private static int _categoria = 0;
	/**
	 * Contador de IdentificadorPV
	 */
	private static int _venta = 0;
	/**
	 * Contador de IdentificadorPS
	 */
	private static int _subasta = 0;
	/**
	 * Contador de IdentificadorLote
	 */
	private static int _lote = 0;
	
	/**
	 * Constructor privado.
	 */
	private ContadorIdentificadores(){}
	
	/**
	 * Reinicia los contadores
	 */
	public static void reiniciarContadores(){
		_categoria = 0;
		_venta = 0;
		_subasta = 0;
		_lote = 0;
	}
	
	/**
	 * Incrementa el contador del tipo especificado y devuelve su valor.
	 * @param tipo Tipo de contador. "categoria", "venta", "subasta" o "lote"
	 * @return El siguiente numero del tipo especificado.
	 */
	public static int siguienteNumero(String tipo){
		int numero = 0;
		if(tipo.equals("categoria")){
			numero = ++_categoria;
		} else if (tipo.equals ("venta")){
			numero =++_venta;
		} else if (tipo.equals ("subasta")){
			numero = ++_subasta;
		} else if (tipo.equals("lote")){
			numero = ++_lote;
		} 
		return numero;
	}
}
