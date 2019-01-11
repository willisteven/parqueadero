package com.ceiba.parqueadero.models.serviceint;

import java.util.List;

import com.ceiba.parqueadero.models.entity.Registro;


public interface IRegistroService {

	public List<Registro> findAll();
	
	public void guardarRegistro(Registro registro);

	public Registro buscarVehiculoPorPlaca(String placa);
	
	public List<Registro> buscarRegistrosVehiculosActivos(int estado);

	
}
