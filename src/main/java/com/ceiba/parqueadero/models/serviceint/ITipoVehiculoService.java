package com.ceiba.parqueadero.models.serviceint;

import com.ceiba.parqueadero.models.entity.TipoVehiculo;

public interface ITipoVehiculoService {
	/**
	 * Metodo que consulta el tipo de vehiculo
	 * @param tipo
	 * @return
	 */
	public TipoVehiculo consultarTipoVehiculo(String tipo);

}
