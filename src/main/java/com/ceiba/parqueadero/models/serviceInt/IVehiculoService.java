package com.ceiba.parqueadero.models.serviceInt;

import java.util.List;

import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.util.RespuestaJson;

public interface IVehiculoService {
	
	public List<Vehiculo> findAll();
		
	public List<Vehiculo> buscarPorTipoVehiculoActivo(String tipoVehiculo, int estado);

	public void guardarVehiculo(Vehiculo vehiculo);
	
}
