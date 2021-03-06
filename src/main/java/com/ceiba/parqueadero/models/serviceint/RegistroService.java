package com.ceiba.parqueadero.models.serviceint;

import java.util.List;

import com.ceiba.parqueadero.models.entity.Registro;

public interface RegistroService {

	/**
	 * Metodo que guarda los registros
	 * 
	 * @param registro
	 */
	public void guardarRegistro(Registro registro);

	/**
	 * Metodo que busca un vehiculo por placa
	 * 
	 * @param placa
	 * @return
	 */
	public Registro buscarVehiculoPorPlaca(String placa);

	/**
	 * Metodo que busca vehiculos activos
	 * 
	 * @param estado
	 * @return
	 */
	public List<Registro> buscarRegistrosVehiculosActivos(int estado);

}
