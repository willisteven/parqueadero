package com.ceiba.parqueadero.models.serviceint;

import java.util.List;


import com.ceiba.parqueadero.models.entity.Vehiculo;


public interface IVehiculoService {
	
	public List<Vehiculo> findAll();
		
	public List<Vehiculo> buscarPorTipoVehiculoActivo(String tipoVehiculo, int estado);

	public void guardarVehiculo(Vehiculo vehiculo);
	
	public boolean vehiculoExiste(String placa,int activo);
	
	public Vehiculo buscarCilindraje(String placa,int estado);
	
	
}
