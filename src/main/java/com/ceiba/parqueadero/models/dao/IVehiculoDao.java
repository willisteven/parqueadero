package com.ceiba.parqueadero.models.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ceiba.parqueadero.models.entity.Vehiculo;

public interface IVehiculoDao extends CrudRepository<Vehiculo, Integer>  {

	
	@Query("SELECT r FROM Vehiculo r WHERE r.idTipoVehiculo.tipo = :tipoVehiculo and r.activo = :activo")
	public Iterable<Vehiculo> findBytipoVehiculoAndActivo(@Param("tipoVehiculo") String tipoVehiculo,@Param("activo") int activo);
	
	public Vehiculo findByplaca(String placa);
	
	@Query("SELECT r FROM Vehiculo r WHERE r.placa = :placa and r.activo =:activo")
	public Vehiculo findByPlacaAndTipoVehiculo(@Param("placa") String placa,@Param("activo") int activo);

	
}
