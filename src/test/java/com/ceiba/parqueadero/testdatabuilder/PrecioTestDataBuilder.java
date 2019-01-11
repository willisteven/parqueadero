package com.ceiba.parqueadero.testdatabuilder;

import com.ceiba.parqueadero.models.entity.Precio;
import com.ceiba.parqueadero.models.entity.TipoTiempo;
import com.ceiba.parqueadero.models.entity.TipoVehiculo;

public class PrecioTestDataBuilder {

	private int idPrecio;
	private TipoVehiculo idTipoVehiculo;
	private TipoTiempo idTipoTiempo;
	private double valor;

	public PrecioTestDataBuilder() {
		this.idPrecio=1;
		this.idTipoVehiculo = new TipoVehiculo("", "");
		this.idTipoTiempo= new TipoTiempo(1,"Hora");
		this.valor= 1000;
	}
	
	public PrecioTestDataBuilder withIdPrecio(int idPrecio) {
		this.idPrecio=idPrecio;
		return this;
	}
	
	public PrecioTestDataBuilder withIdTipoVehiculo(TipoVehiculo idTipoVehiculo) {
		this.idTipoVehiculo=idTipoVehiculo;
		return this;
	}
	
	public PrecioTestDataBuilder withIdTipoTiempo(TipoTiempo idTipoTiempo) {
		this.idTipoTiempo=idTipoTiempo;
		return this;
	}

	public PrecioTestDataBuilder withValor(double valor) {
		this.valor=valor;
		return this;
	}
	
	
	public Precio build() {
		return new Precio(idPrecio,idTipoVehiculo,idTipoTiempo,valor);
	}
	

}
