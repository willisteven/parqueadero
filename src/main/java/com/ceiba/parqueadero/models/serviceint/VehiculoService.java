package com.ceiba.parqueadero.models.serviceint;

import java.util.List;

import com.ceiba.parqueadero.models.entity.Vehiculo;

public interface VehiculoService {

	/**
	 * Metodo que busca vehiculos activos por tipo
	 * 
	 * @param tipoVehiculo
	 * @param estado
	 * @return
	 */
	public List<Vehiculo> buscarPorTipoVehiculoActivo(String tipoVehiculo, int estado);

	/**
	 * Metodo que guarda un vehiculo
	 * 
	 * @param vehiculo
	 */
	public void guardarVehiculo(Vehiculo vehiculo);

	/**
	 * Metodo que busca si un vehiculo existe
	 * 
	 * @param placa
	 * @param activo
	 * @return
	 */
	public boolean vehiculoExiste(String placa, int activo);

	/**
	 * Metodo que busca el cilindraje de un vehiculo
	 * 
	 * @param placa
	 * @param estado
	 * @return
	 */
	public Vehiculo buscarCilindraje(String placa, int estado);

}
