package com.ceiba.parqueadero.unitarias;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parqueadero.models.entity.Precio;
import com.ceiba.parqueadero.models.entity.TipoTiempo;
import com.ceiba.parqueadero.models.entity.TipoVehiculo;
import com.ceiba.parqueadero.models.serviceint.IPrecioService;
import com.ceiba.parqueadero.models.serviceint.ITipoVehiculoService;
import com.ceiba.parqueadero.models.services.VigilanteServiceImpl;
import com.ceiba.parqueadero.reglas.ReglasParqueadero2;
import com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.parqueadero.models.entity.Vehiculo;

import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
//@TestPropertySource(locations = "classpath:test.properties")
public class VigilanteTest {
//prueba cmmit
	@Mock
	private ITipoVehiculoService tipoVehiculoService;

	@Mock
	private IPrecioService precioService;

	@InjectMocks
	private VigilanteServiceImpl vigilante;

	@Test
	public void tipoVehiculoCarro() {
		System.out.println("prueba: ");

		// arrange

		// act

		// assert

	}

	@Test
	public void tipoVehiculoMoto() {
		System.out.println("prueba: ");

		// arrange

		// act

		// assert

	}

	@Test
	public void validarCarrosParqueaderoDisponible() {
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
	public void validarVehiculosParqueaderoNoDisponible() {
		System.out.println("prueba: ");

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
	public void validarMotosParqueaderoDisponible() {
		System.out.println("prueba: ");

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
	public void placaComienzaLetraA() {
		System.out.println("prueba: ");

		// arrange
		ReglasParqueadero2 reglasParqueadero = new ReglasParqueadero2();
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();
		vehiculoTest.withPlaca("AXZ123");
		Vehiculo vehiculo = vehiculoTest.build();
		boolean autorizado;

		// act
		autorizado = reglasParqueadero.validarPlacaLunesDomingos(vehiculo.getPlaca());

		// assert
		Assert.assertFalse(autorizado);

	}

	@Test
	public void placaComienzaLetraDiferenteA() {
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
	public void obtenerValorAPagarMotoConCostoPorCilindraje() {
		// arrange
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();
		vehiculoTest.withIdVehiculo(1);
		vehiculoTest.withIdTipoVehiculo(new TipoVehiculo("moto", "tipo de vehiculo carro"));
		vehiculoTest.withCilindraje(600);

		Vehiculo vehiculo = vehiculoTest.build();

		LocalDateTime localDateTimeIngreso = LocalDateTime.parse("2019-01-10T11:32");
		Date fechaIngreso = Date.from(localDateTimeIngreso.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTimeSalida = LocalDateTime.parse("2019-01-11T14:31");
		Date fechaSalida = Date.from(localDateTimeSalida.atZone(ZoneId.systemDefault()).toInstant());

		Precio precioPorHora = new Precio(1, vehiculo.getIdTipoVehiculo(), new TipoTiempo(1, "hora"), 500);
		Precio precioPorDia = new Precio(3, vehiculo.getIdTipoVehiculo(), new TipoTiempo(3, "dia"), 4000);

		when(this.precioService.obtenerPrecioPorTipoVehiculoYTiempo(vehiculo.getIdTipoVehiculo().getIdTipoVehiculo(),
				1)).thenReturn(precioPorHora);
		when(this.precioService.obtenerPrecioPorTipoVehiculoYTiempo(vehiculo.getIdTipoVehiculo().getIdTipoVehiculo(),
				2)).thenReturn(precioPorDia);
		
		// act
		int cantidadAPagar = this.vigilante.calcularCobroParqueadero(vehiculo, fechaIngreso, fechaSalida);
		
		// assert
		Assert.assertEquals(7500, cantidadAPagar);
	}

}
