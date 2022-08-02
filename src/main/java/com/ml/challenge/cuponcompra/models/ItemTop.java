package com.ml.challenge.cuponcompra.models;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Relacion de items mas 'favoriteados'.
 * @author Diana
 *
 */
public class ItemTop implements Serializable{
	
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 7691939549261506116L;

	/**
	 * item id
	 */
	@JsonProperty("id")
	private String itemId;
	
	/**
	 * Numero de veces que se ha recibido un Item marcado como favorito
	 */
	@JsonProperty("quantity")
	private Long contador;

	/**
	 * Cosntructor
	 */
	public ItemTop() {
		super();
	}

	/**
	 * @param itemId
	 * @param contador
	 */
	public ItemTop(String itemId, Long contador) {
		super();
		this.itemId = itemId;
		this.contador = contador;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the contador
	 */
	public Long getContador() {
		return contador;
	}

	/**
	 * @param contador the contador to set
	 */
	public void setContador(Long contador) {
		this.contador = contador;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
