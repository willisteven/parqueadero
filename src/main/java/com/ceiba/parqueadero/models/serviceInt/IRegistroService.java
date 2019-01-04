package com.ceiba.parqueadero.models.serviceInt;

import java.util.List;

import com.ceiba.parqueadero.models.entity.Registro;
import com.ceiba.parqueadero.util.RespuestaJson;

public interface IRegistroService {

	public List<Registro> findAll();
	
	public void guardarRegistro(Registro registro);

	public Registro buscarVehiculoPorPlaca(String placa);

	
}
