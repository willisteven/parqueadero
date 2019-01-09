package com.ceiba.parqueadero.unitarias;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parqueadero.models.serviceint.ITipoVehiculoService;
import com.ceiba.parqueadero.reglas.ReglasParqueadero2;

import org.mockito.Mock;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
//@TestPropertySource(locations = "classpath:test.properties")
public class VigilanteTest {
//prueba cmmit
	//@Mock
	//private ITipoVehiculoService tipoVehiculoService;

	@Test
	public void tipoVehiculoCarro() {
		// arrange

		// act

		// assert

	}

	@Test
	public void tipoVehiculoMoto() {
		// arrange

		// act

		// assert

	}

	@Test
	public void validarCarrosParqueaderoDisponible() {
		// arrange
		/*ReglasParqueadero2 reglasParqueadero = new ReglasParqueadero2();
		boolean disponible;
		int cantidad = 19;
		String tipo = "carro";
		// act
		disponible = reglasParqueadero.disponibilidadVehiculo(cantidad, tipo);

		// assert
		Assert.assertTrue(disponible);*/

	}

	@Test
	public void validarMotosParqueaderoDisponible() {
		// arrange
	/*	ReglasParqueadero2 reglasParqueadero = new ReglasParqueadero2();
		boolean disponible;
		int cantidad = 9;
		String tipo = "moto";
		// act
		disponible = reglasParqueadero.disponibilidadVehiculo(cantidad, tipo);

		// assert
		Assert.assertTrue(disponible);*/

	}

	@Test
	public void placaComienzaLetraA() {
		// arrange
	/*	ReglasParqueadero2 reglasParqueadero = new ReglasParqueadero2();
		boolean autorizado;
		String placa="AXZ";
		// act
		autorizado = reglasParqueadero.validarPlacaLunesDomingos(placa);

		// assert
		Assert.assertFalse(autorizado);
*/
	}

	@Test
	public void placaComienzaLetraDiferenteA() {
		// arrange

		// act

		// assert

	}

}
