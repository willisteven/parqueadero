package com.ceiba.parqueadero.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.models.dao.IRegistroDao;
import com.ceiba.parqueadero.models.dao.IVehiculoDao;
import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.models.serviceint.IVehiculoService;
import com.ceiba.parqueadero.reglas.ReglasParqueadero;

@Service
public class VehiculoServiceImpl implements IVehiculoService {
	ReglasParqueadero reglasParqueadero= new ReglasParqueadero();


	@Autowired 
	private IVehiculoDao vehiculoDao;
	
	@Autowired 
	private IRegistroDao registroDao;
	
	
	
	@Override
	public List<Vehiculo> findAll() {
		return (List<Vehiculo>) vehiculoDao.findAll();
	}



	@Override
	public List<Vehiculo> buscarPorTipoVehiculoActivo(String tipoVehiculo, int estado) {
		return (List<Vehiculo>) vehiculoDao.findBytipoVehiculoAndActivo(tipoVehiculo, estado);
	}

	@Override
	public void guardarVehiculo(Vehiculo vehiculo) {
		vehiculoDao.save(vehiculo);		
	}

	

}
