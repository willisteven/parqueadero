package com.ceiba.parqueadero.constantes;

public final class Constantes {

	private Constantes() {
		throw new IllegalStateException("Utility class");
	}

	public static final int ACTIVO = 1;
	public static final int INACTIVO = 0;

	public static final String NO_AUTORIZADO = "No puede ingresar porque no está en un dia hábil";
	public static final String NO_CUPO_DISPONIBLE = "No hay cupo Disponible";
	public static final String YA_ESTA_PARQUEADERO = "Este vehiculo ya esta en el parqueadero";
	public static final String NO_PERMITIDO = "Este vehiculo no esta permitido";
	public static final String ERROR_SERVICIO = "Se produjo un error en el servicio";
	public static final String VEHICULO_INGRESADO = "El Vehiculo fue Ingresado al parqueadero correctamente";
	public static final String VEHICULO_NO_ESTA_PARQUEADERO = "El vehiculo no se encuentra en el parqueadero actualmente";

	public static final int VALOR_HORA = 3600000;
	public static final int VALOR_DIA = 86400000;
	
	public static final int ID_HORA=1;
	public static final int ID_DIA=2;

	public static final int CILINDRAJE_TOPE = 500;
	public static final int VALOR_CILINDRAJE_EXTRA = 2000;

	/**
	 * Valid from and valid to TCRM date format
	 */
	public static final String DATE_RESPONSE_FORMAT = "EEE, d MMM yyyy HH:mm:ss Z";

	/**
	 * TCRM query value format
	 */
	public static final String VALUE_QUERY_FORMAT = "#0.00";

	/**
	 * Web Service end point
	 */
	public static final String WEB_SERVICE_URL = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL";

}
