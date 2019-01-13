package com.ceiba.parqueadero.models.serviceint;

import java.rmi.RemoteException;
import java.text.ParseException;

import com.ceiba.parqueadero.util.RespuestaJson;

public interface ITrmSuperFinancieraService {
	public RespuestaJson obtenerTrm() throws ParseException, RemoteException;

}
