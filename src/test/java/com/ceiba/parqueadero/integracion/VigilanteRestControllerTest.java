package com.ceiba.parqueadero.integracion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ceiba.parqueadero.ParqueaderoApplication;
import com.ceiba.parqueadero.constantes.Constantes;
import com.ceiba.parqueadero.models.services.RegistroServiceImpl;
import com.ceiba.parqueadero.objetosnegocio.VehiculoNegocio;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = ParqueaderoApplication.class)
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class VigilanteRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Mock
	private RegistroServiceImpl service;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRealizarRegistroVehiculo() throws Exception {
		// arrange

		JSONObject jsonVehiculo = new JSONObject();
		jsonVehiculo.put("tipo", "carro");
		jsonVehiculo.put("placa", "XYZ123");
		jsonVehiculo.put("cilindraje", 0);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/registroVehiculo")
				.contentType(MediaType.APPLICATION_JSON).content(jsonVehiculo.toJSONString());
		// act
		mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testValorTRM() throws Exception {
		// arrange
		RequestBuilder requestBuilder = get("/api/trmsuperfinanciera").contentType(MediaType.APPLICATION_JSON);
		// act
		mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testGetVehiculosParqueadero() throws Exception {
		// arrange
		RequestBuilder requestBuilder = get("/api/vehiculosParqueadero").contentType(MediaType.APPLICATION_JSON);
		// act
		mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testCilindrajeMoto() throws Exception {
		this.mockMvc.perform(get("/api/cilindraje/{placa}", "XYZ123")).andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void testCilindrajeMotoNoEsta() throws Exception {
		this.mockMvc.perform(get("/api/cilindraje/{placa}", "XYZ123")).andDo(print()).andExpect(status().isNotFound());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRealizarSalida() throws Exception {
		// arrange

		JSONObject jsonVehiculo1 = new JSONObject();
		jsonVehiculo1.put("tipo", "carro");
		jsonVehiculo1.put("placa", "XYZ567");
		jsonVehiculo1.put("cilindraje", 0);

		RequestBuilder requestBuilder1 = MockMvcRequestBuilders.post("/api/registroVehiculo")
				.contentType(MediaType.APPLICATION_JSON).content(jsonVehiculo1.toJSONString());

		mockMvc.perform(requestBuilder1).andDo(print()).andExpect(status().isOk());

		JSONObject jsonVehiculo = new JSONObject();
		jsonVehiculo.put("tipo", "carro");
		jsonVehiculo.put("placa", "XYZ567");
		jsonVehiculo.put("cilindraje", 0);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/salidaVehiculo")
				.contentType(MediaType.APPLICATION_JSON).content(jsonVehiculo.toJSONString());
		// act
		mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
	}

}
