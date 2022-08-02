package com.ml.challenge.cuponcompra.services;

import java.util.List;
import java.util.Set;

import com.ml.challenge.cuponcompra.models.ItemTop;

/**
 * Definicion de metodos que operan sobre la bitacora de favoritos
 * @author Diana
 *
 */
public interface BitacoraFavoritosService {

	/**
	 * Registra en base de datos los items favoritos que se reciben en las peticiones
	 * @param itemsId
	 */
	void guardarItemsFavoritos(Set<String> itemsId);
	
	/**
	 * Obtiene los items favoritos con mas registros en base de datos
	 * @param numeroElementos - numero de regitros a obtener
	 * @return
	 */
	List<ItemTop> obtenerTopFavoritos(int numeroElementos);
}
