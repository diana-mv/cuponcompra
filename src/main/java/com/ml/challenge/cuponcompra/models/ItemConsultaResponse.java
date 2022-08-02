package com.ml.challenge.cuponcompra.models;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Respuesta consulta de items
 * @author Diana
 *
 */
public class ItemConsultaResponse {
	
	/**
	 * Codigo de respuesta
	 */
	@JsonProperty("code")
	private String codigo;	
	
	/**
	 * Body, datos de Item
	 */
	@JsonProperty("body")
	private Item datos;
	
	/**
	 * 
	 */
	public ItemConsultaResponse() {
		super();
		
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the datos
	 */
	public Item getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(Item datos) {
		this.datos = datos;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
