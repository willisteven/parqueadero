package com.ceiba.parqueadero.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ceiba.parqueadero.models.entity.Registro;


public interface IRegistroDao  extends CrudRepository<Registro, Integer>  {
	
	@Query("SELECT r FROM Registro r WHERE r.vehiculo.placa = :placa and r.salidaFecha is null")
	public Registro obtenerVehiculoActivo(@Param("placa") String placa);

}
