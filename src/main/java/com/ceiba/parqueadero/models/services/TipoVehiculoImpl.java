package com.ceiba.parqueadero.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.models.dao.ITipoVehiculoDao;
import com.ceiba.parqueadero.models.entity.TipoVehiculo;
import com.ceiba.parqueadero.models.serviceint.TipoVehiculoService;

@Service
public class TipoVehiculoImpl implements TipoVehiculoService{

	@Autowired
	private ITipoVehiculoDao tipoVehiculoDao;
	
	@Override
	public TipoVehiculo consultarTipoVehiculo(String tipo) {
		return tipoVehiculoDao.obtenerTipoVehiculo(tipo);
	}

}
