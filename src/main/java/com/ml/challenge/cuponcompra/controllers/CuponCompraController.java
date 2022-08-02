package com.ml.challenge.cuponcompra.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ml.challenge.cuponcompra.models.ItemTop;
import com.ml.challenge.cuponcompra.models.ItemsCuponRequest;
import com.ml.challenge.cuponcompra.models.ItemsCuponResponse;
import com.ml.challenge.cuponcompra.services.BitacoraFavoritosService;
import com.ml.challenge.cuponcompra.services.CuponCompraService;

/**
 * Controller manejo de peticiones
 * @author Diana
 *
 */
@RestController
public class CuponCompraController {

	/**
	 * logger
	 */
	Logger logger = LoggerFactory.getLogger(CuponCompraController.class);
	
	/**
	 * Services
	 */
	@Autowired
	private CuponCompraService cuponCompraService;
	
	/**
	 * Servicios bitacora de favoritos
	 */
	@Autowired
	private BitacoraFavoritosService bitacoraFavoritosService;
	
	/**
	 * Determina los items que pueden ser aplicables a un cupon
	 * @param itemsCuponPeticion
	 * @return
	 */
	@PostMapping(path = "/coupon", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ItemsCuponResponse determinarItemCuponAplicable(@RequestBody ItemsCuponRequest itemsCuponPeticion) {
		logger.info("---- Entra a determinarItemCuponAplicable -----");
		logger.info("---- itemsCuponPeticion: {}", itemsCuponPeticion);

		return cuponCompraService.maximizarGastoCupon(itemsCuponPeticion);
	}
	
	/**
	 * Obtiene el top 5 de los items mas 'favoriteados'
	 * @param itemsCuponPeticion
	 * @return
	 */
	@GetMapping(path = "/coupon/stats")
	public List<ItemTop> obtenerTopFavoritos() {
		logger.info("---- Entra a obtenerTopFavoritos -----");

		return bitacoraFavoritosService.obtenerTopFavoritos(5);
	}
	
}
