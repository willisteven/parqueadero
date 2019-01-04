package com.ceiba.parqueadero.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ceiba.parqueadero.models.entity.Precio;

public interface IPrecioDao extends CrudRepository<Precio, Integer> {

	@Query("SELECT p FROM Precio p WHERE p.idTipoVehiculo.idTipoVehiculo = :idTipoVehiculo and p.idTipoTiempo.idTipoTiempo = :id_tiempo")
	public Precio obtenerPrecioPorTipoVehiculoYTiempo(@Param("idTipoVehiculo") int idTipoVehiculo, @Param("id_tiempo") int idTiempo);

}
