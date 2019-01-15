package com.ceiba.parqueadero.models.services;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parqueadero.constantes.Constantes;
import com.ceiba.parqueadero.models.entity.Precio;
import com.ceiba.parqueadero.models.entity.Registro;
import com.ceiba.parqueadero.models.entity.TipoTiempo;
import com.ceiba.parqueadero.models.entity.TipoVehiculo;
import com.ceiba.parqueadero.models.serviceint.IPrecioService;
import com.ceiba.parqueadero.models.serviceint.IRegistroService;
import com.ceiba.parqueadero.models.serviceint.ITipoVehiculoService;
import com.ceiba.parqueadero.models.serviceint.IVehiculoService;
import com.ceiba.parqueadero.models.services.VigilanteServiceImpl;
import com.ceiba.parqueadero.testdatabuilder.RegistroTestDataBuilder;
import com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.parqueadero.util.RespuestaJson;
import com.ceiba.parqueadero.models.entity.Vehiculo;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
//@TestPropertySource(locations = "classpath:test.properties")
public class VigilanteServiceTest {
//prueba cmmit
	@Mock
	private ITipoVehiculoService tipoVehiculoService;

	@Mock
	private IVehiculoService vehiculoService;

	@Mock
	private IPrecioService precioService;

	@Mock
	private IRegistroService registroService;

	@InjectMocks
	private VigilanteServiceImpl vigilante;

	@org.junit.Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testValidarTipoVehiculoCarro() {

		// arrange
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();
		vehiculoTest.withIdTipoVehiculo(new TipoVehiculo("carro", "Tipo Vehiculo Carro"));
		Vehiculo vehiculo = vehiculoTest.build();

		TipoVehiculo tipoVehiculo = new TipoVehiculo("carro", "Tipo vehiculo carro");
		when(this.tipoVehiculoService.consultarTipoVehiculo("carro")).thenReturn(tipoVehiculo);

		// act
		tipoVehiculo = this.vigilante.validarTipoVehiculo(vehiculo.getIdTipoVehiculo().getTipo());

		// assert
		Assert.assertNotNull(tipoVehiculo);

	}

	@Test
	public void testValidarTipoVehiculoMoto() {
		// arrange
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();
		vehiculoTest.withIdTipoVehiculo(new TipoVehiculo("moto", "Tipo Vehiculo Moto"));
		Vehiculo vehiculo = vehiculoTest.build();

		TipoVehiculo tipoVehiculo = new TipoVehiculo("moto", "Tipo vehiculo moto");
		when(this.tipoVehiculoService.consultarTipoVehiculo("moto")).thenReturn(tipoVehiculo);

		// act
		tipoVehiculo = this.vigilante.validarTipoVehiculo(vehiculo.getIdTipoVehiculo().getTipo());

		// assert

		Assert.assertNotNull(tipoVehiculo);
	}

	@Test
	public void testValidarTipoVehiculoInvalido() {
		// arrange
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();
		vehiculoTest.withIdTipoVehiculo(new TipoVehiculo("bus", "Tipo Vehiculo Bus"));
		Vehiculo vehiculo = vehiculoTest.build();

		TipoVehiculo tipoVehiculo = new TipoVehiculo("bus", "Tipo vehiculo Bus");
		when(this.tipoVehiculoService.consultarTipoVehiculo("carro")).thenReturn(tipoVehiculo);
		// act
		tipoVehiculo = this.vigilante.validarTipoVehiculo(vehiculo.getIdTipoVehiculo().getTipo());

		// assert

		Assert.assertNull(tipoVehiculo);
	}

	@Test
	public void testCalcularCobroParqueaderoCarro() {
		// arrange
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();
		vehiculoTest.withIdVehiculo(1);
		vehiculoTest.withIdTipoVehiculo(new TipoVehiculo("carro", "tipo de vehiculo carro"));
		vehiculoTest.withCilindraje(0);

		Vehiculo vehiculo = vehiculoTest.build();

		LocalDateTime localDateTimeIngreso = LocalDateTime.parse("2019-01-10T11:32");
		Date fechaIngreso = Date.from(localDateTimeIngreso.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTimeSalida = LocalDateTime.parse("2019-01-11T14:31");
		Date fechaSalida = Date.from(localDateTimeSalida.atZone(ZoneId.systemDefault()).toInstant());

		Precio precioPorHora = new Precio(1, vehiculo.getIdTipoVehiculo(), new TipoTiempo(1, "hora"), 1000);
		Precio precioPorDia = new Precio(3, vehiculo.getIdTipoVehiculo(), new TipoTiempo(3, "dia"), 8000);

		when(this.precioService.obtenerPrecioPorTipoVehiculoYTiempo(vehiculo.getIdTipoVehiculo().getIdTipoVehiculo(),
				1)).thenReturn(precioPorHora);
		when(this.precioService.obtenerPrecioPorTipoVehiculoYTiempo(vehiculo.getIdTipoVehiculo().getIdTipoVehiculo(),
				2)).thenReturn(precioPorDia);

		// act
		int cantidadAPagar = this.vigilante.calcularCobroParqueadero(vehiculo, fechaIngreso, fechaSalida);

		// assert
		Assert.assertEquals(11000, cantidadAPagar);
	}

	@Test
	public void testCalcularCobroParqueaderoMotoPorCilindraje() {
		// arrange
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();
		vehiculoTest.withIdVehiculo(1);
		vehiculoTest.withIdTipoVehiculo(new TipoVehiculo("moto", "tipo de vehiculo moto"));
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

	@SuppressWarnings("unchecked")
	@Test
	public void testGetVehiculoJson() {
		// arrange
		JSONObject jsonVehiculo = new JSONObject();
		jsonVehiculo.put("tipo", "carro");
		jsonVehiculo.put("placa", "ZAS567");
		jsonVehiculo.put("cilindraje", 0);

		TipoVehiculo tipoVehiculo = new TipoVehiculo("carro", "Tipo vehiculo carro");
		when(this.tipoVehiculoService.consultarTipoVehiculo("carro")).thenReturn(tipoVehiculo);

		// act
		Vehiculo vehiculo = this.vigilante.getVehiculoJson(jsonVehiculo);

		// assert
		Assert.assertEquals("ZAS567", vehiculo.getPlaca());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testgetVehiculoJsonTipoNull() {
		// arrange
		JSONObject jsonVehiculo = new JSONObject();
		jsonVehiculo.put("tipo", "bus");
		jsonVehiculo.put("placa", "ZAS589");
		jsonVehiculo.put("cilindraje", 0);

		TipoVehiculo tipoVehiculo = null;
		when(this.tipoVehiculoService.consultarTipoVehiculo("bus")).thenReturn(tipoVehiculo);

		// act
		Vehiculo vehiculo = this.vigilante.getVehiculoJson(jsonVehiculo);

		// assert
		Assert.assertNull(vehiculo);

	}

	@Test
	public void testGuardarVehiculoRegistro() {
		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		int valor = 0;
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		Date fechaEntrada = new Date();
		Registro registroDeEntrada = new Registro(1, fechaEntrada, null, 0, vehiculo);
		doNothing().when(vehiculoService).guardarVehiculo(vehiculo);
		doNothing().when(registroService).guardarRegistro(registroDeEntrada);
		// act
		this.vigilante.guardarVehiculoRegistro(vehiculo);
		// assert
		Assert.assertEquals(0, valor);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRealizarRegistroVehiculo() {
		// arrange
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();

		List<Vehiculo> listVehiculos = new ArrayList<Vehiculo>();
		RespuestaJson respuestaJson;
		JSONObject jsonVehiculo = new JSONObject();
		jsonVehiculo.put("tipo", "carro");
		jsonVehiculo.put("placa", "ZAS589");
		jsonVehiculo.put("cilindraje", 0);
		boolean isVehiculoExiste = false;
		TipoVehiculo tipoVehiculo = new TipoVehiculo("carro", "Tipo vehiculo carro");

		listVehiculos.add(vehiculoTest.build());
		when(this.tipoVehiculoService.consultarTipoVehiculo("carro")).thenReturn(tipoVehiculo);

		when(this.vehiculoService.vehiculoExiste("ZAS589", Constantes.ACTIVO)).thenReturn(isVehiculoExiste);

		when(this.vehiculoService.buscarPorTipoVehiculoActivo(tipoVehiculo.getTipo(), Constantes.ACTIVO))
				.thenReturn(listVehiculos);

		// act
		respuestaJson = this.vigilante.realizarRegistroVehiculo(jsonVehiculo);
		// assert
		Assert.assertEquals(200, respuestaJson.getCodigoRespuesta());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testNoPermitirIngresoSinDisponibilidad() {
		// arrange
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();

		List<Vehiculo> listVehiculos = new ArrayList<Vehiculo>();
		RespuestaJson respuestaJson;
		JSONObject jsonVehiculo = new JSONObject();
		jsonVehiculo.put("tipo", "carro");
		jsonVehiculo.put("placa", "ZAS589");
		jsonVehiculo.put("cilindraje", 0);
		boolean isVehiculoExiste = false;
		TipoVehiculo tipoVehiculo = new TipoVehiculo("carro", "Tipo vehiculo carro");

		for (int i = 0; i <= 20; i++) {
			listVehiculos.add(vehiculoTest.build());
		}
		when(this.tipoVehiculoService.consultarTipoVehiculo("carro")).thenReturn(tipoVehiculo);

		when(this.vehiculoService.vehiculoExiste("ZAS589", Constantes.ACTIVO)).thenReturn(isVehiculoExiste);

		when(this.vehiculoService.buscarPorTipoVehiculoActivo(tipoVehiculo.getTipo(), Constantes.ACTIVO))
				.thenReturn(listVehiculos);

		// act
		respuestaJson = this.vigilante.realizarRegistroVehiculo(jsonVehiculo);
		// assert
		Assert.assertEquals(Constantes.NO_CUPO_DISPONIBLE, respuestaJson.getDescripcion());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testNoPermitirIngresoYaEstaParqueadero() {
		// arrange
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();

		List<Vehiculo> listVehiculos = new ArrayList<Vehiculo>();
		RespuestaJson respuestaJson;
		JSONObject jsonVehiculo = new JSONObject();
		jsonVehiculo.put("tipo", "carro");
		jsonVehiculo.put("placa", "ZAS589");
		jsonVehiculo.put("cilindraje", 0);
		boolean isVehiculoExiste = true;
		TipoVehiculo tipoVehiculo = new TipoVehiculo("carro", "Tipo vehiculo carro");

		listVehiculos.add(vehiculoTest.build());

		when(this.tipoVehiculoService.consultarTipoVehiculo("carro")).thenReturn(tipoVehiculo);

		when(this.vehiculoService.vehiculoExiste("ZAS589", Constantes.ACTIVO)).thenReturn(isVehiculoExiste);

		when(this.vehiculoService.buscarPorTipoVehiculoActivo(tipoVehiculo.getTipo(), Constantes.ACTIVO))
				.thenReturn(listVehiculos);

		// act
		respuestaJson = this.vigilante.realizarRegistroVehiculo(jsonVehiculo);
		// assert
		Assert.assertEquals(Constantes.YA_ESTA_PARQUEADERO, respuestaJson.getDescripcion());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRealizarSalida() {
		// arrange
		RegistroTestDataBuilder registroTest = new RegistroTestDataBuilder();
		Registro registro = registroTest.build();
		RespuestaJson respuestaJson;
		JSONObject jsonVehiculo = new JSONObject();
		jsonVehiculo.put("tipo", "carro");
		jsonVehiculo.put("placa", "XYZ123");
		jsonVehiculo.put("cilindraje", 0);

		when(this.registroService.buscarVehiculoPorPlaca("XYZ123")).thenReturn(registro);
		when(this.tipoVehiculoService.consultarTipoVehiculo("carro"))
				.thenReturn(registro.getVehiculo().getIdTipoVehiculo());
		Precio precioPorHora = new Precio(1, registro.getVehiculo().getIdTipoVehiculo(), new TipoTiempo(1, "hora"),
				1000);
		Precio precioPorDia = new Precio(3, registro.getVehiculo().getIdTipoVehiculo(), new TipoTiempo(3, "dia"), 8000);

		when(this.precioService
				.obtenerPrecioPorTipoVehiculoYTiempo(registro.getVehiculo().getIdTipoVehiculo().getIdTipoVehiculo(), 1))
						.thenReturn(precioPorHora);
		when(this.precioService
				.obtenerPrecioPorTipoVehiculoYTiempo(registro.getVehiculo().getIdTipoVehiculo().getIdTipoVehiculo(), 2))
						.thenReturn(precioPorDia);

		// act
		respuestaJson = this.vigilante.realizarSalidaVehiculo(jsonVehiculo);
		// assert
		Assert.assertEquals(200, respuestaJson.getCodigoRespuesta());
	}

}
