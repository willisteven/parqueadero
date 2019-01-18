package com.ceiba.parqueadero.models.serviceint;

import java.rmi.RemoteException;

import java.util.List;

import com.ceiba.parqueadero.models.exception.VigilanteInternalServerErrorException;
import com.ceiba.parqueadero.models.exception.VigilanteNotFoundException;
import com.ceiba.parqueadero.objetosnegocio.VehiculoNegocio;
import com.ceiba.parqueadero.objetosnegocio.VehiculosParqueaderoNegocio;
import com.ceiba.parqueadero.util.RespuestaJson;

public interface VigilanteService {

	/**
	 * Metodo que realiza el registro del vehiculo
	 * 
	 * @param vehiculo
	 * @return
	 */
	public RespuestaJson realizarRegistroVehiculo(VehiculoNegocio vehiculo);

	/**
	 * Metodo que realiza la salida del vehiculo
	 * 
	 * @param vehiculo
	 * @return
	 */
	public RespuestaJson realizarSalidaVehiculo(VehiculoNegocio vehiculo);

	/**
	 * Metodo que obtiene los vehiculos en el parqueadero
	 * 
	 * @return
	 */
	public List<VehiculosParqueaderoNegocio> getVehiculosParqueadero();

	/**
	 * Metodo que obtiene el cilindraje de las motos
	 * 
	 * @param placa
	 * @return
	 */
	public VehiculoNegocio getCilindrajeMoto(String placa) throws VigilanteNotFoundException;

	/**
	 * Metodo que obtiene la TRM
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public RespuestaJson obtenerTrm() throws RemoteException, VigilanteInternalServerErrorException;
}
