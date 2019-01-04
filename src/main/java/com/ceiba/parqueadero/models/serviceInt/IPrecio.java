package com.ceiba.parqueadero.models.serviceInt;

import com.ceiba.parqueadero.models.entity.Precio;

public interface IPrecio {

	Precio obtenerPrecioPorTipoVehiculoYTiempo(int idTipoVehiculo, int idTiempo);

}
