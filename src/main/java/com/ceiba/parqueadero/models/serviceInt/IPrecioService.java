package com.ceiba.parqueadero.models.serviceInt;

import com.ceiba.parqueadero.models.entity.Precio;

public interface IPrecioService {

	Precio obtenerPrecioPorTipoVehiculoYTiempo(int idTipoVehiculo, int idTiempo);

}
