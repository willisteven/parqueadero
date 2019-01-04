package com.ceiba.parqueadero.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.parqueadero.models.dao.IRegistroDao;
import com.ceiba.parqueadero.models.dao.IVehiculoDao;
import com.ceiba.parqueadero.models.entity.Registro;
import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.models.serviceint.IRegistroService;

@Service
public class RegistroServiceImpl implements IRegistroService{

	@Autowired
	private IRegistroDao registroDao;
	
	@Autowired
	private IVehiculoDao vehiculoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Registro> findAll() {
		return (List<Registro>) registroDao.findAll();
	}

	@Override
	public void guardarRegistro(Registro registro) {
		registroDao.save(registro);
	}

	@Override
	public Registro buscarVehiculoPorPlaca(String placa) {
		Registro registro=this.registroDao.obtenerVehiculoActivo(placa);
		
		if(registro!=null) {
			Vehiculo vehiculo= this.vehiculoDao.findByplaca(placa);
			return new Registro(registro.getIngresoFecha(), registro.getSalidaFecha(), 0, vehiculo);
		}
		return null;
	}


}
