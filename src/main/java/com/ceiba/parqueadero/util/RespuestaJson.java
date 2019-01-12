package com.ceiba.parqueadero.util;

public class RespuestaJson {
	
	private int codigoRespuesta;
	private boolean valido;
	private String descripcion;

	
	public RespuestaJson(int codigoRespuesta,boolean valido, String descripcion) {
		super();
		this.codigoRespuesta = codigoRespuesta;
		this.valido=valido;
		this.descripcion = descripcion;
	}
	public int getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(int codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public boolean getValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
