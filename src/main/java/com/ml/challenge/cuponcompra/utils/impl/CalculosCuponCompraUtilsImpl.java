package com.ml.challenge.cuponcompra.utils.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ml.challenge.cuponcompra.models.Item;
import com.ml.challenge.cuponcompra.utils.CalculosCuponCompraUtils;

/**
 * Calulos sobre items aplicables a un cupon
 * @author Diana
 *
 */
@Component
public class CalculosCuponCompraUtilsImpl implements CalculosCuponCompraUtils{
	/**
	 * logger
	 */
	Logger logger = LoggerFactory.getLogger(CalculosCuponCompraUtilsImpl.class);
	
	/**
	 * Maximo gasto 
	 */
	private Double maximoGasto = 0.0;
	
	/**
	 * Conjunto de items que fueron sumados para obtener el maximo gasto
	 */
	private List<Item> mejorCombinacion = new ArrayList<>();
	
	@Override
	public List<Item> obtenerMaximoGasto(List<Item> items, Double montoCupon) {
		logger.info("----- Entra a obtener obtenerMaximoGasto -----");
		mejorCombinacion = new ArrayList<>();
		maximoGasto = 0.0;
		 
		sumarCombinacionRecursiva(items, montoCupon, new ArrayList<>());
		
		//Si no encuentra una respuesta
		if(mejorCombinacion == null) {
			mejorCombinacion = new ArrayList<>();
		}
		
		return mejorCombinacion;
	}

	/**
	 * Metodo recursivo para probar la suma de montos para todas las combinaciones
	 * @param preciosItems
	 * @param montoCupon
	 * @param preciosItemsParcial
	 */
	private void sumarCombinacionRecursiva(List<Item> preciosItems, Double montoCupon, List<Item> preciosItemsParcial) {
		
		Double sumaParcial = 0.0;
		
		for (Item precio : preciosItemsParcial) {
			sumaParcial += precio.getPrice();
	    }
		
		//Si encuentra el monto exacto termina
		if(sumaParcial.compareTo(montoCupon)==0) {
			logger.info("----- Encontro suma monto exacto -----");
			maximoGasto = sumaParcial;
			mejorCombinacion = new ArrayList<>(preciosItemsParcial);
			return;
		}	
		//Aqui mantiene la mejor combinacion y gasto maximo al momento
		else if( sumaParcial.compareTo(montoCupon) < 0  && sumaParcial.compareTo(0.0) > 0 && sumaParcial.compareTo(maximoGasto) > 0) {			
			maximoGasto = sumaParcial;
			mejorCombinacion = new ArrayList<>(preciosItemsParcial);
		}			
		
		for (int i = 0; i < preciosItems.size(); i++) {
			List<Item> pendientes = new ArrayList<>();
			Item n = preciosItems.get(i);
			for (int j = i + 1; j < preciosItems.size(); j++) {
				pendientes.add(preciosItems.get(j));				
			}
			
			List<Item> parcialAsumar = new ArrayList<>(preciosItemsParcial);
			parcialAsumar.add(n);			
			sumarCombinacionRecursiva(pendientes, montoCupon, parcialAsumar);
		}
	}
}