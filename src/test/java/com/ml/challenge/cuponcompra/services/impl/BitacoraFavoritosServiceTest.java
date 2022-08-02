package com.ml.challenge.cuponcompra.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ml.challenge.cuponcompra.models.ItemTop;
import com.ml.challenge.cuponcompra.repositories.BitacoraFavoritosRepository;

/**
 * Pruebas
 * @author Diana
 *
 */
@SpringBootTest
class BitacoraFavoritosServiceTest {

	@Autowired
	private BitacoraFavoritosServiceImpl bitacoraFavoritosServiceImpl;
	
	@MockBean
	private BitacoraFavoritosRepository bitacoraFavoritosRepository;
	
	/**
	 * Prueba /coupon/stats
	 * Obtiene top 5
	 */
	@Test
	void obtenerTopFavoritos() {
		List<Object[]> itemsTop = new ArrayList<>();
		Object[] itemTop1 = new Object[]{"MLM1447798739",25L};
		Object[] itemTop2 = new Object[]{"MLM1411691052",20L};
		Object[] itemTop3 = new Object[]{"MLM940167363",18L};
		Object[] itemTop4 = new Object[]{"MLM1462733265",18L};
		Object[] itemTop5 = new Object[]{"MLM1425866303",16L};
		itemsTop.add(itemTop1);
		itemsTop.add(itemTop2);
		itemsTop.add(itemTop3);
		itemsTop.add(itemTop4);
		itemsTop.add(itemTop5);
		
		Mockito.when(bitacoraFavoritosRepository.obtenerConteoFavoritos()).thenReturn(itemsTop);
		List<ItemTop> items = bitacoraFavoritosServiceImpl.obtenerTopFavoritos(5);
		
		assertThat(items).isNotNull();
	}
	
	/**
	 * Prueba /coupon/stats
	 * No encuentra resultados
	 */
	@Test
	void obtenerTopFavoritos_SinResultados() {
		Mockito.when(bitacoraFavoritosRepository.obtenerConteoFavoritos()).thenReturn(null);
		List<ItemTop> items = bitacoraFavoritosServiceImpl.obtenerTopFavoritos(5);
		
		assertThat(items).isEmpty();
	}
}
