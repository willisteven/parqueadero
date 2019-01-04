package com.ceiba.parqueadero.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.models.dao.IPrecioDao;
import com.ceiba.parqueadero.models.entity.Precio;
import com.ceiba.parqueadero.models.serviceInt.IPrecioService;

@Service
public class PrecioServiceImpl implements IPrecioService{

	@Autowired
	private IPrecioDao precioDao;
	
	@Override
	public Precio obtenerPrecioPorTipoVehiculoYTiempo(int idTipoVehiculo, int idTiempo) {
		Precio precio = this.precioDao.obtenerPrecioPorTipoVehiculoYTiempo(idTipoVehiculo, idTiempo);
		return null;
	}

}
