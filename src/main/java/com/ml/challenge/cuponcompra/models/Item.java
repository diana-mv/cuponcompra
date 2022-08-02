package com.ml.challenge.cuponcompra.models;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Mapeo con atributos de 'item' 
 * @author Diana
 *
 */
public class Item {
	
	/**
	 * Id
	 */
	private String id;	
	
	/**
	 * Precio
	 */
	private Double price;
	
	//Mapeo de mas atributos si se requiere
	
	/**
	 * Cosntructor
	 */
	public Item() {
		super();
	}
	
	/**
	 * @param id
	 * @param price
	 */
	public Item(String id, Double price) {
		super();
		this.id = id;
		this.price = price;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
