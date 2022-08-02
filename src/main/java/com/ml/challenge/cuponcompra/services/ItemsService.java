package com.ml.challenge.cuponcompra.services;

import java.util.List;

import com.ml.challenge.cuponcompra.models.Item;

/**
 * Definicion de metodos para Items
 * @author Diana
 *
 */
public interface ItemsService {
	
	/**
	 * Consulta un set de items para obtenes los valores de sus atributos
	 * @param itemsId - set de item_id
	 * @param atributos - atributos que se requieren en la respuesta
	 * @param token - token de acceso de la aplicacion
	 * @return
	 */
	List<Item> obtenerItems(String itemsId, String atributos,  String token);
}
