package com.ml.challenge.cuponcompra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ml.challenge.cuponcompra.repositories.models.BitacoraFavoritos;

/**
 * Repositorio bitacora de items favoritos recibidos
 * @author Diana
 *
 */
@Repository
public interface BitacoraFavoritosRepository extends JpaRepository<BitacoraFavoritos, Long> {

	/**
	 * Obtiene el numero de repeticiones de un Item 
	 * @return
	 */
	@Query("SELECT DISTINCT(f.itemId), count(*) as contador FROM BitacoraFavoritos f GROUP BY itemId ORDER BY contador DESC")
	List<Object[]> obtenerConteoFavoritos();
}
