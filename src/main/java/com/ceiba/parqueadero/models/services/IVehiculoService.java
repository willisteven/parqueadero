package com.ceiba.parqueadero.models.services;

import java.util.List;

import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.util.RespuestaJson;

public interface IVehiculoService {
	
	public List<Vehiculo> findAll();
	
	public RespuestaJson realizarRegistroVehiculo(Vehiculo vehiculo);

}
