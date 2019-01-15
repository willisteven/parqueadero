package com.ceiba.parqueadero.reglas;

import org.junit.Assert;
import org.junit.Test;

import com.ceiba.parqueadero.models.entity.TipoVehiculo;
import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.reglas.ReglasParqueadero2;
import com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

public class ReglasParqueadero2Test {

	@Test
	public void TestDisponibilidadVehiculoCarro() {
		System.out.println("prueba: ");

		// arrange
		ReglasParqueadero2 reglasParqueadero = new ReglasParqueadero2();
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();
		vehiculoTest.withIdTipoVehiculo(new TipoVehiculo("carro", "tipo de vehiculo carro"));
		Vehiculo vehiculo = vehiculoTest.build();
		boolean disponible;
		int cantidad = 19;

		// act
		disponible = reglasParqueadero.disponibilidadVehiculo(cantidad, vehiculo.getIdTipoVehiculo().getTipo());

		// assert
		Assert.assertTrue(disponible);

	}


	@Test
	public void TestDisponibilidadVehiculoNoDisponible() {
		// arrange
		ReglasParqueadero2 reglasParqueadero = new ReglasParqueadero2();
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();
		vehiculoTest.withIdTipoVehiculo(new TipoVehiculo("carro", "tipo de vehiculo carro"));
		Vehiculo vehiculo = vehiculoTest.build();
		boolean disponible;
		int cantidad = 20;

		// act
		disponible = reglasParqueadero.disponibilidadVehiculo(cantidad, vehiculo.getIdTipoVehiculo().getTipo());

		// assert
		Assert.assertFalse(disponible);

	}

	@Test
	public void TestDisponibilidadVehiculoMoto() {

		// arrange
		ReglasParqueadero2 reglasParqueadero = new ReglasParqueadero2();
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();
		vehiculoTest.withIdTipoVehiculo(new TipoVehiculo("moto", "tipo de vehiculo moto"));
		Vehiculo vehiculo = vehiculoTest.build();
		boolean disponible;
		int cantidad = 9;

		// act
		disponible = reglasParqueadero.disponibilidadVehiculo(cantidad, vehiculo.getIdTipoVehiculo().getTipo());

		// assert
		Assert.assertTrue(disponible);

	}
	
	@Test
	public void TestValidarPlacaLunesDomingos() {
		// arrange
		ReglasParqueadero2 reglasParqueadero = new ReglasParqueadero2();
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();
		vehiculoTest.withPlaca("XZA234");
		Vehiculo vehiculo = vehiculoTest.build();
		boolean autorizado;

		// act
		autorizado = reglasParqueadero.validarPlacaLunesDomingos(vehiculo.getPlaca());

		// assert
		Assert.assertTrue(autorizado);

	}


}
