package com.ceiba.parqueadero.util;

public class RespuestaJson {
	
	private int codigoRespuesta;
	private String descripcion;

	
	public RespuestaJson(int codigoRespuesta, String descripcion) {
		super();
		this.codigoRespuesta = codigoRespuesta;
		descripcion = descripcion;
	}
	public int getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(int codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		descripcion = descripcion;
	}
	
	
	
}
