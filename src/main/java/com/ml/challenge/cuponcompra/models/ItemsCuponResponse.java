package com.ml.challenge.cuponcompra.models;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Relacion de items aplicables a un cupon.
 * Objeto response para el servicio.
 * @author Diana
 *
 */
public class ItemsCuponResponse implements Serializable{
	
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 7691939549261506116L;

	/**
	 * Lista de items marcados como favoritos
	 */
	@JsonProperty("item_ids")
	private List<String> items;
	
	/**
	 * Monto del cupon
	 */
	@JsonProperty("total")
	private Double gastoTotal;

	/**
	 * Cosntructor
	 */
	public ItemsCuponResponse() {
		super();
	}

	/**
	 * @param items
	 * @param gastoTotal
	 */
	public ItemsCuponResponse(List<String> items, Double gastoTotal) {
		super();
		this.items = items;
		this.gastoTotal = gastoTotal;
	}

	/**
	 * @return the items
	 */
	public List<String> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<String> items) {
		this.items = items;
	}

	/**
	 * @return the gastoTotal
	 */
	public Double getGastoTotal() {
		return gastoTotal;
	}

	/**
	 * @param gastoTotal the gastoTotal to set
	 */
	public void setGastoTotal(Double gastoTotal) {
		this.gastoTotal = gastoTotal;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
