package com.ceiba.parqueadero.testdatabuilder;

import java.util.Date;

import com.ceiba.parqueadero.constantes.Constantes;
import com.ceiba.parqueadero.models.entity.Registro;
import com.ceiba.parqueadero.models.entity.TipoVehiculo;
import com.ceiba.parqueadero.models.entity.Vehiculo;

public class RegistroTestDataBuilder {
	private int idRegistro;
	private Date ingresoFecha;
	private Date salidaFecha;
	private double valorPagar;
	private Vehiculo vehiculo;

	public RegistroTestDataBuilder() {
		idRegistro = 1;
		ingresoFecha = new Date();
		salidaFecha = new Date();
		valorPagar = 2000;
		vehiculo = new Vehiculo(1, "XYZ123", new TipoVehiculo("carro", "tipo de vehiculo carro"), 0, Constantes.ACTIVO);
	}

	public RegistroTestDataBuilder withIdRegistro(int idRegistro) {
		this.idRegistro = idRegistro;
		return this;
	}

	public RegistroTestDataBuilder withIngresoFecha(Date ingresoFecha) {
		this.ingresoFecha = ingresoFecha;
		return this;
	}

	public RegistroTestDataBuilder withSalidaFecha(Date salidaFecha) {
		this.salidaFecha = salidaFecha;
		return this;
	}

	public RegistroTestDataBuilder withValorPagar(double valorPagar) {
		this.valorPagar = valorPagar;
		return this;
	}

	public RegistroTestDataBuilder withVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}

	public Registro build() {
		return new Registro(idRegistro, ingresoFecha, salidaFecha, valorPagar, vehiculo);
	}

}
