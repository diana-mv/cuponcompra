package com.ml.challenge.cuponcompra.webclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.ml.challenge.cuponcompra.models.AccessTokenResponse;
import com.ml.challenge.cuponcompra.repositories.ParametrosConfigRepository;
import com.ml.challenge.cuponcompra.repositories.models.ParametrosConfig;

import reactor.core.publisher.Mono;

/**
 * Cliente para consumo de API MELI
 * @author Diana
 *
 */
@Component
public class ApiMercadoLibreWebClient {

	/**
	 * Logger
	 */
    private static final Logger logger = LoggerFactory.getLogger(ApiMercadoLibreWebClient.class);
    
    /**
     * WebClient
     */
    private final WebClient webClient;
   
    /**
     * Aplication id
     */
    @Value("${meli.application.id}") 
    private String appId;
    
    /**
     * secret key de cliente
     */
    @Value("${meli.secret.key}") 
    private String secretKey;
    
    /**
     * Token para actualizar
     */
    @Value("${meli.refresh.token}") 
    private String refreshToken;
    
    /**
     * Ruta del recurso para actualizar token
     */
    @Value("${meli.api.uri.recurso.refresh}") 
    private String recursoRefreshToken;
    
    /**
	 * Repositorio parametros de configuracion
	 */
	@Autowired
	private ParametrosConfigRepository parametrosConfigRepository;
    
    /**
     * Constructor, inicializa webClient
     * @param webClientBuilder
     * @param uri
     */
	public ApiMercadoLibreWebClient(WebClient.Builder webClientBuilder, @Value("${meli.api.uri}") String uri) {
		webClient = webClientBuilder.baseUrl(uri)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).build();
	}

	/**
	 * Actualiza el token de acceso
	 * @return
	 */
	public String actualizarToken(ParametrosConfig token) {
		logger.info("----- Entra a actualizar token: {} -----", token);

		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("grant_type", "refresh_token");
		formData.add("client_id", appId);
		formData.add("client_secret", secretKey);
		formData.add("refresh_token", refreshToken);

		Mono<AccessTokenResponse> response = webClient.post().uri(recursoRefreshToken)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.body(BodyInserters.fromFormData(formData))
				.retrieve()
				.bodyToMono(AccessTokenResponse.class);
		
		AccessTokenResponse accessTokenResponse = response.block();
		
		token.setValor(accessTokenResponse !=null ? accessTokenResponse.getAccessToken() : null);
		parametrosConfigRepository.save(token);
		
		logger.info("----- Datos token actualizado: {} -----", token);
		
		return token.getValor();
	}

	/**
	 * @return the webClient
	 */
	public WebClient getWebClient() {
		return webClient;
	}	
}