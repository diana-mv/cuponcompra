package com.ml.challenge.cuponcompra.repositories.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Mapeo de entidad de parametros
 * @author Diana
 *
 */
@Entity
@Table(name="tp_params_config")
public class ParametrosConfig implements Serializable{

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -6790513919357345474L;

	/**
	 * Identificador de registro unico
	 */
	@Id
	@Column(name="id_param")
	private Long id;
	
	/**
	 * Nombre del parametro
	 */
	@Column(name="ch_nombre")
	private String nombre;
	
	/**
	 * Valor de parametro
	 */
	@Column(name="ch_valor")
	private String valor;
	
	/**
	 * Constructor
	 */
	public ParametrosConfig() {
		super();
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
