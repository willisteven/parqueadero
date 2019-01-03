package com.ceiba.parqueadero.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ceiba.parqueadero.models.entity.Vehiculo;

public interface IVehiculoDao extends CrudRepository<Vehiculo, Integer>  {

}
