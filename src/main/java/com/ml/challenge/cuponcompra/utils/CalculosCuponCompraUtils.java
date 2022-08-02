package com.ml.challenge.cuponcompra.utils;

import java.util.List;

import com.ml.challenge.cuponcompra.models.Item;

/**
 * Calulos sobre items aplicables a un cupon
 * @author Diana
 *
 */
public interface CalculosCuponCompraUtils {
	
	/**
	 * Dado un conjunto de Items busca aquellos donde la suma de sus precios 
	 * genere el mayor gasto cercano o exacto al monto del cupon especificado
	 * @param items
	 * @param montoCupon
	 * @return
	 */
	List<Item> obtenerMaximoGasto(List<Item> items, Double montoCupon);
}
