package com.ml.challenge.cuponcompra.constants;

/**
 * Constantes
 * @author Diana
 *
 */
public class CuponCompraConstants {

	/**
	 * Expresion regular para validar el formato de item_id
	 */
	public static final String REGEX_ITEM = "^[a-zA-Z]{3}\\d+$";
	
	/**
	 * Expresion regular para validar el formato de monto
	 */
	public static final String REGEX_MONTO = "\\d+(\\.?\\d+)?";
	
	/**
	 * Caracter coma
	 */
	public static final String CARACTER_COMA = ",";
	
	/**
	 * Caracter guin bajo
	 */
	public static final String CARACTER_GUION_BAJO = "_";
	
	/**
	 * Nombre de header Authorization
	 */
	public static final String HEADER_AUTHORIZATION = "Authorization";
	
	/**
	 * Nombre de header Bearer
	 */
	public static final String AUTHORIZATION_BEARER= "Bearer";
	
	/**
	 * Atributos a recuperar en consulta de items
	 */
	public static final String ATRIBUTOS_ID_PRECIO = "id,price";

	/**
	 * Constructor
	 */
	private CuponCompraConstants() {
		super();
	}
}
