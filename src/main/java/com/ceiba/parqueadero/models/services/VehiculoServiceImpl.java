package com.ceiba.parqueadero.models.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.models.dao.IRegistroDao;
import com.ceiba.parqueadero.models.dao.IVehiculoDao;
import com.ceiba.parqueadero.models.entity.Registro;
import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.models.serviceInt.IVehiculoService;
import com.ceiba.parqueadero.reglas.ReglasParqueadero;
import com.ceiba.parqueadero.util.RespuestaJson;

@Service
public class VehiculoServiceImpl implements IVehiculoService {
	ReglasParqueadero reglasParqueadero= new ReglasParqueadero();
	public final int estado=1;


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
