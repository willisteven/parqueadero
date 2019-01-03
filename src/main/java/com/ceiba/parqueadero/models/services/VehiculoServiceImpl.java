package com.ceiba.parqueadero.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.models.dao.IVehiculoDao;
import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.util.RespuestaJson;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired 
	private IVehiculoDao vehiculoDao;
	
	@Override
	public List<Vehiculo> findAll() {
		return (List<Vehiculo>) vehiculoDao.findAll();
	}
	
	@Override
	public RespuestaJson realizarRegistroVehiculo(Vehiculo vehiculo) {
		vehiculoDao.save(vehiculo);
		return null;
	}
	

}
