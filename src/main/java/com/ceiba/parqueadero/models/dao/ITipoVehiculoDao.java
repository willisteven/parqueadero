package com.ceiba.parqueadero.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ceiba.parqueadero.models.entity.Registro;
import com.ceiba.parqueadero.models.entity.TipoVehiculo;;

public interface ITipoVehiculoDao  extends CrudRepository<TipoVehiculo, Integer>  {

	@Query("SELECT r FROM TipoVehiculo r WHERE r.tipo = :tipo")
	public TipoVehiculo obtenerTipoVehiculo(@Param("tipo") String tipo);
}
