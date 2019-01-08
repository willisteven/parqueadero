package com.ceiba.parqueadero.models.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.models.dao.IRegistroDao;
import com.ceiba.parqueadero.models.dao.IVehiculoDao;

import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.models.serviceint.IVehiculoService;
import com.ceiba.parqueadero.reglas.ReglasParqueadero2;

@Service
public class VehiculoServiceImpl implements IVehiculoService {
	ReglasParqueadero2 reglasParqueadero;

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

	@Override
	public boolean vehiculoExiste(String placa, int activo) {
		boolean isExiste = true;
		Vehiculo vehiculoExiste = vehiculoDao.findByPlacaAndTipoVehiculo(placa, activo);
		if (vehiculoExiste == null) {
			isExiste = false;
		}
		return isExiste;
	}

	@Override
	public Vehiculo buscarCilindraje(String placa,int activo) {
		Vehiculo vehiculo = vehiculoDao.findByPlacaAndTipoVehiculo(placa,activo);
		if (vehiculo != null) {
			return vehiculo;
		} else {
			return null;
		}
	}

}
