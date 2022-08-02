package com.ml.challenge.cuponcompra.exceptions;

/**
 * Excepcion Bad Request
 * @author Diana
 *
 */
public class BadRequestException extends RuntimeException{
	
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -9145524114946608986L;
	
	/**
	 * Descripcion general
	 */
	private static final String DESCRIPCION = "Bad Request Exception (400)";

	/**
	 * Constructor
	 */
	public BadRequestException(String detalle) {
		super(new StringBuilder(DESCRIPCION).append(". ").append(detalle).toString());
	}

	
}
