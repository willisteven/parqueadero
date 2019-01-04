package com.ceiba.parqueadero.models.dto;

import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VehiculoDto {
	  private String placa;
	  private String tipo;
	  private int cilindraje;
	  
	  public VehiculoDto() {}

	  // you can do any transforamtion/validation here
	  public static VehiculoDto fromEntity(Vehiculo vehiculo){
		VehiculoDto dto = new VehiculoDto();
	    dto.setPlaca(vehiculo.getPlaca());
	    dto.setTipo(vehiculo.getIdTipoVehiculo().getTipo());
	    dto.setCilindraje(vehiculo.getCilindraje());
	    return dto;
	  }

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	  
	  

}
