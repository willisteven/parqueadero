package com.ceiba.parqueadero.models.serviceint;

import java.rmi.RemoteException;

import com.ceiba.parqueadero.util.RespuestaJson;

public interface TrmSuperFinancieraService {
	/**
	 * Metodo que obtiene el TRM
	 * @return
	 * @throws RemoteException
	 */
	public RespuestaJson obtenerTrm() throws RemoteException;

}
