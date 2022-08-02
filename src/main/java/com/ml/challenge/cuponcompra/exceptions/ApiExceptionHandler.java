package com.ml.challenge.cuponcompra.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Manejador de excepciones centralizado
 * @author Diana
 *
 */
@ControllerAdvice
public class ApiExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
		BadRequestException.class,
		HttpMessageNotReadableException.class
	})
	@ResponseBody
	public ErrorMessage badRequest(HttpServletRequest request, Exception exeption) {		
		return new ErrorMessage(exeption, request.getRequestURI(), HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({
		Exception.class
	})
	@ResponseBody
	public ErrorMessage unexpectedException(HttpServletRequest request, Exception exeption) {
		return new ErrorMessage(exeption, request.getRequestURI(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
