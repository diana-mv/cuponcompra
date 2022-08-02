package com.ml.challenge.cuponcompra.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.challenge.cuponcompra.models.ItemTop;
import com.ml.challenge.cuponcompra.repositories.BitacoraFavoritosRepository;
import com.ml.challenge.cuponcompra.repositories.models.BitacoraFavoritos;
import com.ml.challenge.cuponcompra.services.BitacoraFavoritosService;

/**
 * Implementacion de metodos que operan sobre la bitacora de favoritos
 * @author Diana
 *
 */
@Service("bitacoraFavoritos")
public class BitacoraFavoritosServiceImpl implements BitacoraFavoritosService{
	
	/**
	 * logger
	 */
	Logger logger = LoggerFactory.getLogger(BitacoraFavoritosServiceImpl.class);
	
	/**
	 * Repositorio de bitacora de favoritos
	 */
	@Autowired
	private BitacoraFavoritosRepository bitacoraFavoritosRepository;
	
	@Override
	public void guardarItemsFavoritos(Set<String> itemsId) {
		logger.info("----- Entra a guardarItemsFavoritos -----");
		for(String itemId: itemsId) {
			bitacoraFavoritosRepository.save(new BitacoraFavoritos(itemId));
		}
	}

	@Override
	public List<ItemTop> obtenerTopFavoritos(int numeroElementos) {
		logger.info("----- Entra a obtenerTopFavoritos, top {} -----", numeroElementos);
		
		List<ItemTop> listItemsTop = new ArrayList<>();
		List<Object[]> itemsTop = bitacoraFavoritosRepository.obtenerConteoFavoritos();
		
		if(itemsTop != null && !itemsTop.isEmpty()) {
			int numElementos = itemsTop.size() < numeroElementos ? itemsTop.size() : numeroElementos;
			
			for(int i=0; i<numElementos; i++) {
				listItemsTop.add(new ItemTop((String)itemsTop.get(i)[0], (Long)itemsTop.get(i)[1]));
			}
		}		
		return listItemsTop;
	}	
}
