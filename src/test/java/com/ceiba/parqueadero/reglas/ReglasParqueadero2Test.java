package com.ceiba.parqueadero.reglas;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.parqueadero.models.entity.TipoVehiculo;
import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.reglas.ReglasParqueadero2;
import com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

public class ReglasParqueadero2Test {

	@Test
	public void TestDisponibilidadVehiculoCarro() {
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

	@Test
	public void TestValidarPlacaLunesDomingosComnienzaA() {
		// arrange
		ReglasParqueadero2 reglasParqueadero = new ReglasParqueadero2();
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();
		vehiculoTest.withPlaca("AZA234");
		Vehiculo vehiculo = vehiculoTest.build();
		boolean autorizado;

		Calendar c = Mockito.mock(Calendar.class);
		Mockito.when(c.get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.SUNDAY);
		reglasParqueadero.setCalendar(c);
		// act
		autorizado = reglasParqueadero.validarPlacaLunesDomingos(vehiculo.getPlaca());
		Assert.assertTrue(autorizado);
	}
	
	@Test
	public void TestValidarPlacaLunesDomingosNoAutorizado() {
		// arrange
		ReglasParqueadero2 reglasParqueadero = new ReglasParqueadero2();
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();
		vehiculoTest.withPlaca("AZA234");
		Vehiculo vehiculo = vehiculoTest.build();
		boolean autorizado;

		Calendar c = Mockito.mock(Calendar.class);
		Mockito.when(c.get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.FRIDAY);
		reglasParqueadero.setCalendar(c);
		// act
		autorizado = reglasParqueadero.validarPlacaLunesDomingos(vehiculo.getPlaca());
		Assert.assertFalse(autorizado);
	}

}
