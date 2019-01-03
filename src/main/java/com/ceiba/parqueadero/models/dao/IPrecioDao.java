package com.ceiba.parqueadero.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ceiba.parqueadero.models.entity.Precio;

public interface IPrecioDao extends CrudRepository<Precio, Integer> {

}
