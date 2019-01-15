package com.ceiba.parqueadero.models.services;

import java.rmi.RemoteException;
import java.text.DecimalFormat;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.constantes.Constantes;
import com.ceiba.parqueadero.models.serviceint.ITrmSuperFinancieraService;
import com.ceiba.parqueadero.util.RespuestaJson;

import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterfaceProxy;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TcrmResponse;

@Service
public class TrmSuperFinancieraImpl implements ITrmSuperFinancieraService {

	public RespuestaJson obtenerTrm() throws RemoteException {

		DecimalFormat decimalFormat = new DecimalFormat(Constantes.VALUE_QUERY_FORMAT);
		TCRMServicesInterfaceProxy proxy = new TCRMServicesInterfaceProxy(Constantes.WEB_SERVICE_URL);
		TcrmResponse tcrmResponse = proxy.queryTCRM(null);
		return new RespuestaJson(HttpStatus.OK.value(), true, decimalFormat.format(tcrmResponse.getValue()));
	}
}
