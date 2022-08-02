package com.ml.challenge.cuponcompra.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ml.challenge.cuponcompra.exceptions.BadRequestException;
import com.ml.challenge.cuponcompra.models.Item;
import com.ml.challenge.cuponcompra.models.ItemsCuponRequest;
import com.ml.challenge.cuponcompra.models.ItemsCuponResponse;
import com.ml.challenge.cuponcompra.repositories.BitacoraFavoritosRepository;
import com.ml.challenge.cuponcompra.repositories.ParametrosConfigRepository;
import com.ml.challenge.cuponcompra.repositories.models.ParametrosConfig;
import com.ml.challenge.cuponcompra.webclient.ApiMercadoLibreWebClient;

/**
 * Pruebas
 * @author Diana
 *
 */
@SpringBootTest
class CuponCompraServiceTest {

	@Autowired
	private CuponCompraServiceImpl cuponCompraServiceImpl;
	
	@MockBean
	private ItemsServiceImpl itemsServiceImpl;
	
	@MockBean
	private ParametrosConfigRepository parametrosConfigRepository;
	
	@MockBean
	private BitacoraFavoritosRepository bitacoraFavoritosRepository;
	
	@MockBean
	private ApiMercadoLibreWebClient apiMeliWebClient;
	
	/**
	 * Token
	 */
	private ParametrosConfig paramToken;
	
	/**
	 * Respuesta items
	 */
	private List<Item> itemsRespuesta;
	
	@BeforeEach
	void init() {
		paramToken = new ParametrosConfig();
		paramToken.setNombre("token");		
		paramToken.setValor("APP_USR-4755023303329308-080200-ed85c0383522906d55afb12ff7cbf309-1168158404");
		
		itemsRespuesta = new ArrayList<>();
		itemsRespuesta.add(new Item("MLM1425866303",2179.0));
		itemsRespuesta.add(new Item("MLM1431232496",4436.43));
		itemsRespuesta.add(new Item("MLM1420589121",5499.0));
		itemsRespuesta.add(new Item("MLM1361846034",3749.0));
		itemsRespuesta.add(new Item("MLM1425866303",5384.36));
	}
	
	
	/**
	 * Prueba /coupon
	 * Happy path
	 */
	@Test
	void determinarItemCuponAplicable_success() {
		ItemsCuponRequest itemsCuponPeticion = new ItemsCuponRequest();
		itemsCuponPeticion.setMonto(8000.0);
		List<String> listItems = List.of("MLM1425866303","MLM1431232496","MLM1420589121","MLM1361846034","MLM1425866303");
		itemsCuponPeticion.setItems(listItems);
		
		Mockito.when(parametrosConfigRepository.findByNombre("token")).thenReturn(paramToken);
		
		Mockito.when(itemsServiceImpl.obtenerItems(anyString(), anyString(), anyString())).thenReturn(itemsRespuesta);
		
		ItemsCuponResponse response = cuponCompraServiceImpl.maximizarGastoCupon(itemsCuponPeticion);
		assertThat(response).isNotNull();
		assertThat(response.getItems()).isNotEmpty();
	}
	
	
	/**
	 * Prueba /coupon
	 * La consulta al API para obtener datos no regresa info
	 */
	@Test
	void determinarItemCuponAplicable_SinResultados() {
		ItemsCuponRequest itemsCuponPeticion = new ItemsCuponRequest();
		itemsCuponPeticion.setMonto(8000.0);
		List<String> listItems = List.of("MLM1425866303","MLM1431232496","MLM1420589121","MLM1361846034","MLM1425866303");
		itemsCuponPeticion.setItems(listItems);
		
		Mockito.when(parametrosConfigRepository.findByNombre("token")).thenReturn(paramToken);
		
		//No encuentra info de items
		Mockito.when(itemsServiceImpl.obtenerItems(anyString(), anyString(), anyString())).thenReturn(new ArrayList<>());
		
		ItemsCuponResponse response = cuponCompraServiceImpl.maximizarGastoCupon(itemsCuponPeticion);
		assertThat(response).isNotNull();
		assertThat(response.getItems()).isEmpty();
	}
	
	/**
	 * Prueba /coupon
	 * Recibe uno de los parametros nulo (precio o la lista de items)
	 */
	@Test
	void determinarItemCuponAplicable_ParametroNulo() {
		ItemsCuponRequest itemsCuponPeticion = new ItemsCuponRequest();
		itemsCuponPeticion.setMonto(8000.0);
		//Parametro nulo
		itemsCuponPeticion.setItems(null);
		String msgFalla = null;
		
		try {
			cuponCompraServiceImpl.maximizarGastoCupon(itemsCuponPeticion);
		}
		catch(BadRequestException e) {
			msgFalla = e.getMessage();
		}
		assertThat(msgFalla).isNotNull();
	}
	
	/**
	 * Prueba /coupon
	 * El monto del cupon es menor que cualquier precio, no hay una respuesta
	 */
	@Test
	void determinarItemCuponAplicable_montoMenor() {
		ItemsCuponRequest itemsCuponPeticion = new ItemsCuponRequest();
		//Monto menor que los precios de items
		itemsCuponPeticion.setMonto(700.0);
		List<String> listItems = List.of("MLM1425866303","MLM1431232496","MLM1420589121","MLM1361846034","MLM1425866303");
		itemsCuponPeticion.setItems(listItems);
		
		Mockito.when(parametrosConfigRepository.findByNombre("token")).thenReturn(paramToken);
		
		//No encuentra info de items
		Mockito.when(itemsServiceImpl.obtenerItems(anyString(), anyString(), anyString())).thenReturn(itemsRespuesta);
		
		ItemsCuponResponse response = cuponCompraServiceImpl.maximizarGastoCupon(itemsCuponPeticion);
		assertThat(response).isNotNull();
		assertThat(response.getItems()).isEmpty();
		assertThat(response.getGastoTotal()).isEqualTo(0.0);
	}
	
	/**
	 * Prueba /coupon
	 * El monto del cupon es muy alto, resultado suma todos los items
	 */
	@Test
	void determinarItemCuponAplicable_montoAlto() {
		ItemsCuponRequest itemsCuponPeticion = new ItemsCuponRequest();
		//Monto menor que los precios de items
		itemsCuponPeticion.setMonto(25000.0);
		List<String> listItems = List.of("MLM1425866303","MLM1431232496","MLM1420589121","MLM1361846034","MLM1425866303");
		itemsCuponPeticion.setItems(listItems);
		
		Mockito.when(parametrosConfigRepository.findByNombre("token")).thenReturn(paramToken);
		
		//No encuentra info de items
		Mockito.when(itemsServiceImpl.obtenerItems(anyString(), anyString(), anyString())).thenReturn(itemsRespuesta);
		
		ItemsCuponResponse response = cuponCompraServiceImpl.maximizarGastoCupon(itemsCuponPeticion);
		assertThat(response).isNotNull();
		assertThat(response.getItems()).hasSize(5);
		assertThat(response.getGastoTotal()).isEqualTo(21247.79);
	}
}
