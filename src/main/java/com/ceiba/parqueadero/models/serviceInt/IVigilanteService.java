package com.ceiba.parqueadero.models.serviceInt;

import java.util.List;

import org.json.simple.JSONObject;

import com.ceiba.parqueadero.models.dto.VehiculoDto;
import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.util.RespuestaJson;

public interface IVigilanteService {

	public RespuestaJson realizarRegistroVehiculo(JSONObject vehiculo);

	public RespuestaJson realizarSalidaVehiculo(JSONObject vehiculo);

	public List<Vehiculo> obtenerVehiculosDelParqueadero();
	
	public RespuestaJson obtenerTRM();

}
