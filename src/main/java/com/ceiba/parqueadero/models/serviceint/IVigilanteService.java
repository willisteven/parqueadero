package com.ceiba.parqueadero.models.serviceint;

import java.rmi.RemoteException;

import java.util.List;

import org.json.simple.JSONObject;



import com.ceiba.parqueadero.util.RespuestaJson;

public interface IVigilanteService {

	/**
	 * Metodo que realiza el registro del vehiculo
	 * @param vehiculo
	 * @return
	 */
	public RespuestaJson realizarRegistroVehiculo(JSONObject vehiculo);

	/**
	 * Metodo que realiza la salida del vehiculo
	 * @param vehiculo
	 * @return
	 */
	public RespuestaJson realizarSalidaVehiculo(JSONObject vehiculo);

	/**
	 * Metodo que obtiene los vehiculos en el parqueadero
	 * @return
	 */
	public List<JSONObject> getVehiculosParqueadero();
		
	/**
	 * Metodo que obtiene el cilindraje de las motos
	 * @param placa
	 * @return
	 */
	public JSONObject getCilindrajeMoto(String placa);

	/**
	 * Metodo que obtiene la TRM
	 * @return
	 * @throws RemoteException
	 */
	public RespuestaJson obtenerTrm() throws RemoteException;
}
