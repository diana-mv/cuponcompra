package com.ml.challenge.cuponcompra.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Mensaje de error general
 * @author Diana
 *
 */
public class ErrorMessage implements Serializable{
	
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 8834645697087583447L;

	/**
	 * fecha hora cuando el error ocurre
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	
	/**
	 * Estatus
	 */
	private HttpStatus status;
	
	/**
	 * Clase de excepcion
	 */
	private String exception;
	
	/**
	 * Mensaje
	 */
	private String message;
	
	/**
	 * Ruta
	 */
	private String path;

	/**
	 * @param exception
	 * @param message
	 * @param path
	 */
	public ErrorMessage(Exception exception, String path, HttpStatus status) {		
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.exception = exception.getClass().getSimpleName();
		this.message = exception.getMessage();
		this.path = path;
	}

	/**
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * @return the timestamp
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}	
}
