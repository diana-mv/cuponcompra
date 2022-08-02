package com.ml.challenge.cuponcompra.utils.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ml.challenge.cuponcompra.constants.CuponCompraConstants;
import com.ml.challenge.cuponcompra.exceptions.BadRequestException;
import com.ml.challenge.cuponcompra.models.ItemsCuponRequest;
import com.ml.challenge.cuponcompra.utils.CuponCompraUtils;

/**
 * Implementacion de metodos de CuponCompraUtils
 * @author Diana
 *
 */
@Component
public class CuponCompraUtilsImpl implements CuponCompraUtils{
	
	/**
	 * logger
	 */
	Logger logger = LoggerFactory.getLogger(CuponCompraUtilsImpl.class);

	public void validarRequest(ItemsCuponRequest itemsCuponPeticion) {
		
		if(itemsCuponPeticion == null) {
			throw new BadRequestException("Parámetros 'items_id' y 'amount' son requeridos.");			
		}
		else if(itemsCuponPeticion.getItems() == null || itemsCuponPeticion.getItems().isEmpty()) {			
			throw new BadRequestException("Parámetro 'items_id' es requerido.");
		}
		else if(itemsCuponPeticion.getMonto() == null) {			
			throw new BadRequestException("Parámetro 'amount' es requerido.");
		}			
	}
	
	@Override
	public Set<String> obtenerItemsValidosNoDuplicados(List<String> items) {
		logger.info("----- Entra a obtenerItemsValidosNoDuplicados ");
		Set<String> itemsValidos = new HashSet<>();

		for (String id : items) {
			if (validarRegExp(id, CuponCompraConstants.REGEX_ITEM)) {
				itemsValidos.add(id);
			}
			else {
				throw new BadRequestException(new StringBuilder("El elemento ").append(id).append(" en 'items_id' no presenta el formato correcto").toString());
			}
		}
		
		logger.info("----- Items validos: {}",itemsValidos);
		return itemsValidos;
	}

	/**
	 * Valida que una cadena cumpla con una expresion regular
	 * @param cadena
	 * @param patron
	 * @return
	 */
	private boolean validarRegExp(String cadena, String patron) {
		Pattern pattern = Pattern.compile(patron);
		Matcher matcher = pattern.matcher(cadena);
		return matcher.matches();
	}
	
	@Override
	public String[] obtenerGruposItemsDeConsulta(Set<String> items) {
		logger.info("----- Entra a obtenerGruposItemsDeConsulta ");
		int cont = 1;
		StringBuilder sb = new StringBuilder();

		for (String item : items) {
			sb = sb.append(item);
			
			if (cont++ == 20) {
				sb = sb.append(CuponCompraConstants.CARACTER_GUION_BAJO);
				cont = 1;
			} else {
				sb = sb.append(CuponCompraConstants.CARACTER_COMA);
			}
		}
		return StringUtils.chop(sb.toString()).split(CuponCompraConstants.CARACTER_GUION_BAJO);
	}	
}