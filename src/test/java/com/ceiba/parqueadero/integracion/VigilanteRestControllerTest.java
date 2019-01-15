package com.ceiba.parqueadero.integracion;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import com.ceiba.parqueadero.models.services.RegistroServiceImpl;

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
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/trmsuperfinanciera")
				.contentType(MediaType.APPLICATION_JSON);
		// act
		mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testGetVehiculosParqueadero() throws Exception {
		// arrange
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/vehiculosParqueadero")
				.contentType(MediaType.APPLICATION_JSON);
		// act
		mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
	}

}
