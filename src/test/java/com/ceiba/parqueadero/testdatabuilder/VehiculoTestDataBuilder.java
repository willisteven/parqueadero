package com.ceiba.parqueadero.testdatabuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ceiba.parqueadero.models.entity.TipoVehiculo;
import com.ceiba.parqueadero.models.entity.Vehiculo;

public class VehiculoTestDataBuilder {

	private int idVehiculo;
	private String placa;
	private TipoVehiculo idTipoVehiculo;
	private int cilindraje;
	private int activo;

	public VehiculoTestDataBuilder() {
		this.idVehiculo = 1;
		this.placa = "";
		this.idTipoVehiculo = new TipoVehiculo("", "");
		this.cilindraje = 400;
		this.activo = 1;
	}
	
	public VehiculoTestDataBuilder withIdVehiculo(int idVehiculo) {
		this.idVehiculo=idVehiculo;
		return this;
	}
	
	public VehiculoTestDataBuilder withPlaca(String placa) {
		this.placa=placa;
		return this;
	}
	
	public VehiculoTestDataBuilder withIdTipoVehiculo(TipoVehiculo idTipoVehiculo) {
		this.idTipoVehiculo=idTipoVehiculo;
		return this;
	}

	public VehiculoTestDataBuilder withCilindraje(int cilindraje) {
		this.cilindraje=cilindraje;
		return this;
	}
	
	public VehiculoTestDataBuilder withActivo(int activo) {
		this.activo=activo;
		return this;
	}
	public Vehiculo build() {
		return new Vehiculo(idVehiculo,placa,idTipoVehiculo,cilindraje,activo);
	}
	
}
