package com.ceiba.parqueadero.models.serviceint;

import com.ceiba.parqueadero.models.entity.Precio;

public interface IPrecioService {

	/**
	 * 
	 * @Author william
	 * @param idTipoVehiculo
	 * @param idTiempo
	 * @return
	 */
	Precio obtenerPrecioPorTipoVehiculoYTiempo(int idTipoVehiculo, int idTiempo);

}
