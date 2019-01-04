package com.ceiba.parqueadero.models.serviceInt;

import java.util.List;

import com.ceiba.parqueadero.models.dto.VehiculoDto;
import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.util.RespuestaJson;

public interface IVigilanteService {

	public RespuestaJson realizarRegistroVehiculo(Vehiculo vehiculo);

	public RespuestaJson realizarSalidaVehiculo(Vehiculo vehiculo);

	public List<Vehiculo> obtenerVehiculosDelParqueadero();
	
	public RespuestaJson obtenerTRM();

}
