package com.ml.challenge.cuponcompra.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.ml.challenge.cuponcompra.constants.CuponCompraConstants;
import com.ml.challenge.cuponcompra.models.Item;
import com.ml.challenge.cuponcompra.models.ItemsCuponRequest;
import com.ml.challenge.cuponcompra.models.ItemsCuponResponse;
import com.ml.challenge.cuponcompra.repositories.ParametrosConfigRepository;
import com.ml.challenge.cuponcompra.repositories.models.ParametrosConfig;
import com.ml.challenge.cuponcompra.services.BitacoraFavoritosService;
import com.ml.challenge.cuponcompra.services.CuponCompraService;
import com.ml.challenge.cuponcompra.services.ItemsService;
import com.ml.challenge.cuponcompra.utils.CalculosCuponCompraUtils;
import com.ml.challenge.cuponcompra.utils.CuponCompraUtils;
import com.ml.challenge.cuponcompra.webclient.ApiMercadoLibreWebClient;

/**
 * Servicios cupon compra
 * @author Diana
 *
 */
@Service("cuponCompraService")
public class CuponCompraServiceImpl implements CuponCompraService{
	
	/**
	 * logger
	 */
	Logger logger = LoggerFactory.getLogger(CuponCompraServiceImpl.class);
	
	/**
	 * Cliente API MeLi
	 */
	@Autowired
	private ApiMercadoLibreWebClient apiMeliWebClient;
	
	/**
	 * Servicios Item
	 */
	@Autowired
	private ItemsService itemsService;
	
	/**
	 * Servicios bitacora de favoritos
	 */
	@Autowired
	private BitacoraFavoritosService bitacoraFavoritosService;
	
	/**
	 * Metodos utils auxiliares
	 */
	@Autowired
	private CuponCompraUtils cuponCompraUtils;
	
	/**
	 * Utilerias para calculo de maximo gasto
	 */
	@Autowired
	private CalculosCuponCompraUtils calculosCuponCompraUtils;
	
	/**
	 * Repositorio parametros de configuracion
	 */
	@Autowired
	private ParametrosConfigRepository parametrosConfigRepository;
	
	@Override
	public ItemsCuponResponse maximizarGastoCupon(ItemsCuponRequest itemsCupon) {
		logger.info("----- Entra a maximizarGastoCupon -----");
		
		cuponCompraUtils.validarRequest(itemsCupon);
		
		//Obtiene token de acceso para API
		ParametrosConfig paramToken = parametrosConfigRepository.findByNombre("token");
		
		//Selecciona solo los Items a procesar
		Set<String> itemsId = cuponCompraUtils.obtenerItemsValidosNoDuplicados(itemsCupon.getItems());
		
		//Registra items favoritos recibidos
		bitacoraFavoritosService.guardarItemsFavoritos(itemsId);
		
		//Agrupa los items para consultar los precios por set
		String[] gruposItems = cuponCompraUtils.obtenerGruposItemsDeConsulta(itemsId);
		
		//Consulta precios de Item
		List<Item> items = new ArrayList<>();
		for(String grupo : gruposItems) {
			try {
				items.addAll(itemsService.obtenerItems(grupo, CuponCompraConstants.ATRIBUTOS_ID_PRECIO, paramToken.getValor()));
			}
			catch(WebClientResponseException e){
				String message = e.getMessage();
				if(message != null  && message.contains("401 Unauthorized")) {
					String token = apiMeliWebClient.actualizarToken(paramToken);
					items.addAll(itemsService.obtenerItems(grupo, CuponCompraConstants.ATRIBUTOS_ID_PRECIO, token));
				}
				else {
					throw e;
				}
			}
		}
		logger.info("----- Items encontrados: {} -----",items);
		
		//Obtiene los items que cumplen con la condicion de maximo gasto
		List<Item> itemsSugeridos = calculosCuponCompraUtils.obtenerMaximoGasto(items, itemsCupon.getMonto());		
		logger.info("----- Items sugeridos: {} -----",itemsSugeridos);
		
		Double monto = 0.0;
		List<String> itemsIdSugeridos = new ArrayList<>();
		for(Item item: itemsSugeridos) {
			monto += item.getPrice();
			itemsIdSugeridos.add(item.getId());
		}
		
		return new ItemsCuponResponse(itemsIdSugeridos, monto);
	}
	
}
