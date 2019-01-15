package com.ceiba.parqueadero.models.serviceint;

import com.ceiba.parqueadero.models.entity.Precio;

public interface IPrecioService {

	/**
	 * Metodo que obtiene el precio dependiendo el tipo de vehiculo y el tiempo
	 * @Author william
	 * @param idTipoVehiculo
	 * @param idTiempo
	 * @return
	 */
	Precio obtenerPrecioPorTipoVehiculoYTiempo(int idTipoVehiculo, int idTiempo);

}
