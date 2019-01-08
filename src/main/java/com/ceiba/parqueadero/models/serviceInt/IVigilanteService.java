package com.ceiba.parqueadero.models.serviceint;

import java.util.List;

import org.json.simple.JSONObject;



import com.ceiba.parqueadero.util.RespuestaJson;

public interface IVigilanteService {

	public RespuestaJson realizarRegistroVehiculo(JSONObject vehiculo);

	public RespuestaJson realizarSalidaVehiculo(JSONObject vehiculo);

	public List<JSONObject> getVehiculosParqueadero();
		
	public JSONObject getCilindrajeMoto(String placa);

}
