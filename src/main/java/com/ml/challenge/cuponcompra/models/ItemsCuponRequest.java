package com.ml.challenge.cuponcompra.models;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Relacion de items aplicables a un cupon.
 * Objeto request.
 * @author Diana
 *
 */
public class ItemsCuponRequest implements Serializable{
	
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
	@JsonProperty("amount")
	private Double monto;

	/**
	 * Cosntructor
	 */
	public ItemsCuponRequest() {
		super();
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
	 * @return the monto
	 */
	public Double getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(Double monto) {
		this.monto = monto;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
