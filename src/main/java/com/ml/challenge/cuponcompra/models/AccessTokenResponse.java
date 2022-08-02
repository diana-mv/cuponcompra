package com.ml.challenge.cuponcompra.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Atributos respuesta de operacion refresh token
 * @author Diana
 *
 */
public class AccessTokenResponse implements Serializable{
	
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -9141666514344947434L;

	/**
	 * Token de acceso
	 */
	@JsonProperty("access_token")
	private String accessToken;
	
	/**
	 * Tipo de token
	 */
	@JsonProperty("token_type")
	private String tipo;
	
	/**
	 * Tiempo de vigencia de token en segundos
	 */
	@JsonProperty("expires_in")
	private Integer duracionSegundos;
	
	/**
	 * Alcance
	 */
	@JsonProperty("scope")
	private String alcance;
	
	/**
	 * id usuario
	 */
	@JsonProperty("user_id")
	private String usuarioId;
	
	/**
	 * token para actualizacion
	 */
	@JsonProperty("refresh_token")
	private String tokenRefresh;

	/**
	 * Constructor
	 */
	public AccessTokenResponse() {
		super();
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the duracionSegundos
	 */
	public Integer getDuracionSegundos() {
		return duracionSegundos;
	}

	/**
	 * @param duracionSegundos the duracionSegundos to set
	 */
	public void setDuracionSegundos(Integer duracionSegundos) {
		this.duracionSegundos = duracionSegundos;
	}

	/**
	 * @return the alcance
	 */
	public String getAlcance() {
		return alcance;
	}

	/**
	 * @param alcance the alcance to set
	 */
	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}

	/**
	 * @return the usuarioId
	 */
	public String getUsuarioId() {
		return usuarioId;
	}

	/**
	 * @param usuarioId the usuarioId to set
	 */
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	/**
	 * @return the tokenRefresh
	 */
	public String getTokenRefresh() {
		return tokenRefresh;
	}

	/**
	 * @param tokenRefresh the tokenRefresh to set
	 */
	public void setTokenRefresh(String tokenRefresh) {
		this.tokenRefresh = tokenRefresh;
	}
}
