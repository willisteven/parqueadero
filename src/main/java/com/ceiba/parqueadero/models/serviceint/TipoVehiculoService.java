package com.ceiba.parqueadero.models.serviceint;

import com.ceiba.parqueadero.models.entity.TipoVehiculo;

public interface TipoVehiculoService {
	/**
	 * Metodo que consulta el tipo de vehiculo
	 * @param tipo
	 * @return
	 */
	public TipoVehiculo consultarTipoVehiculo(String tipo);

}
