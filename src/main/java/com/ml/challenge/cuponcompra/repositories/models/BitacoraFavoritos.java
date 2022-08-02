package com.ml.challenge.cuponcompra.repositories.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Mapeo con entidad tb_favoritos donde se lleva el registro de los items favoritos 
 * recibidos en las peticiones
 * @author Diana
 *
 */
@Entity
@Table(name = "tb_favoritos")
public class BitacoraFavoritos implements Serializable{
	
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 6757991408811503841L;

	/**
	 * Identificador de registro unico
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Id de Item
	 */
	@Column(name="id_item")
	private String itemId;

	/**
	 * 
	 */
	public BitacoraFavoritos() {
		super();
	}

	/**
	 * @param itemId
	 */
	public BitacoraFavoritos(String itemId) {
		super();
		this.itemId = itemId;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
