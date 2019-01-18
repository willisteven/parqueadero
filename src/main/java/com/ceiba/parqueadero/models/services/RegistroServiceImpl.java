package com.ceiba.parqueadero.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.parqueadero.models.dao.IRegistroDao;
import com.ceiba.parqueadero.models.entity.Registro;

import com.ceiba.parqueadero.models.serviceint.RegistroService;

@Service
public class RegistroServiceImpl implements RegistroService{

	@Autowired
	private IRegistroDao registroDao;
	

	@Override
	public void guardarRegistro(Registro registro) {
		registroDao.save(registro);
	}

	@Override
	public Registro buscarVehiculoPorPlaca(String placa) {
		return this.registroDao.obtenerVehiculoActivo(placa);
	}
	
	@Override
	public List<Registro> buscarRegistrosVehiculosActivos(int estado) {
		return registroDao.findByEstado(estado);
	}


}
