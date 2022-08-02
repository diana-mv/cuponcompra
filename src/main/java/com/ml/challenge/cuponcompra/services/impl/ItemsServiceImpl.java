package com.ml.challenge.cuponcompra.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.challenge.cuponcompra.constants.CuponCompraConstants;
import com.ml.challenge.cuponcompra.models.Item;
import com.ml.challenge.cuponcompra.models.ItemConsultaResponse;
import com.ml.challenge.cuponcompra.services.ItemsService;
import com.ml.challenge.cuponcompra.webclient.ApiMercadoLibreWebClient;

/**
 * Implementacion de metodos gestiond de Items
 * @author Diana
 *
 */
@Service
public class ItemsServiceImpl implements ItemsService{
	
	/**
	 * logger
	 */
	Logger logger = LoggerFactory.getLogger(ItemsServiceImpl.class);
	
	/**
	 * Cliente para consumo de API MeLi
	 */
	@Autowired
	private ApiMercadoLibreWebClient apiMeliWebClient;

	@Override
	public List<Item> obtenerItems(String itemsId, String atributos,  String token) {
		logger.info("----- Consultar items: {} ", itemsId);
		
		List<Item> items = new ArrayList<>();
		
		ItemConsultaResponse[] itemsEncontrados =  apiMeliWebClient.getWebClient().get()
				.uri(uriBuilder -> uriBuilder.path("/items").queryParam("ids", itemsId)
						.queryParam("attributes", atributos).build())
				.header(CuponCompraConstants.HEADER_AUTHORIZATION,token)
				.retrieve()				
				.bodyToMono(ItemConsultaResponse[].class).block();
		
		for(ItemConsultaResponse respuesta: itemsEncontrados) {
			if(respuesta.getCodigo().equals("200") && respuesta.getDatos()!=null && respuesta.getDatos().getPrice() !=null) {
				items.add(respuesta.getDatos());
			}
			else {
				logger.info("----- Item no encontrado: {} ", respuesta.getDatos().getId());
			}
		}
		
		return items;
	}
}
