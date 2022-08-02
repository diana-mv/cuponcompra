package com.ml.challenge.cuponcompra.utils;

import java.util.List;
import java.util.Set;

import com.ml.challenge.cuponcompra.models.ItemsCuponRequest;

/**
 * Metodos auxiliares para cupon compra
 * @author Diana
 *
 */
public interface CuponCompraUtils {
	
	void validarRequest(ItemsCuponRequest itemsCuponPeticion);
	
	/**
	 * Obtiene los items_id unicos que cumplen con el formato 
	 * @param itemsCupon
	 * @return
	 */
	Set<String> obtenerItemsValidosNoDuplicados(List<String> itemsCupon);
	
	/**
	 * Genera cadenas concatenando 20 items 
	 * @param items
	 * @return
	 */
	String[] obtenerGruposItemsDeConsulta(Set<String> items);
	
}
