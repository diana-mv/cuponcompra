package com.ml.challenge.cuponcompra.services;

import com.ml.challenge.cuponcompra.models.ItemsCuponRequest;
import com.ml.challenge.cuponcompra.models.ItemsCuponResponse;

/**
 * Servicios para cupon compra
 * @author Diana
 *
 */
public interface CuponCompraService {

	/**
	 * Obtiene la mejor combinacion de items favoritos con el fin de sumar el maximo gasto
	 * lo mas aproximado o igual al monto del cupon 
	 * @param itemsCupon
	 * @return
	 */
	ItemsCuponResponse maximizarGastoCupon(ItemsCuponRequest itemsCupon);
}
