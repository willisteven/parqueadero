package com.ceiba.parqueadero.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ceiba.parqueadero.models.entity.Registro;


public interface IRegistroDao  extends CrudRepository<Registro, Integer>  {

}
