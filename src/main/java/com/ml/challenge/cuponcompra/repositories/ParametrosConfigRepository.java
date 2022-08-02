package com.ml.challenge.cuponcompra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.challenge.cuponcompra.repositories.models.ParametrosConfig;

/**
 * Repositorio de parametros de configuracion
 * @author Diana
 *
 */
@Repository
public interface ParametrosConfigRepository extends JpaRepository<ParametrosConfig, Long>{
	
	/**
	 * Obtiene un parametro por nombre
	 * @param nombre
	 * @return
	 */
	ParametrosConfig findByNombre(String nombre);
	
}
