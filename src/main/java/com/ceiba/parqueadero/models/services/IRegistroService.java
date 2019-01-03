package com.ceiba.parqueadero.models.services;

import java.util.List;

import com.ceiba.parqueadero.models.entity.Registro;
import com.ceiba.parqueadero.util.RespuestaJson;

public interface IRegistroService {

	public List<Registro> findAll();
	
}
