package com.ceiba.parqueadero.models.services;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ceiba.parqueadero.models.dao.IPrecioDao;
import com.ceiba.parqueadero.models.entity.Precio;
import com.ceiba.parqueadero.testdatabuilder.PrecioTestDataBuilder;

public class PrecioServiceTest {
	
	@Mock
	private IPrecioDao precioDao;
	
	@InjectMocks
	private PrecioServiceImpl precioService;
	
	@org.junit.Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testObtenerPrecioPorTipoVehiculoYTiempo() {
		// arrange	
		int idTipoVehiculo=1;
		int idTiempo=1;
		PrecioTestDataBuilder precioTestDataBuilder = new PrecioTestDataBuilder();
		Precio precioTest = precioTestDataBuilder.build();
		when(this.precioDao.obtenerPrecioPorTipoVehiculoYTiempo(idTipoVehiculo, idTiempo)).thenReturn(precioTest);

		// act
		Precio precio = precioService.obtenerPrecioPorTipoVehiculoYTiempo(idTipoVehiculo, idTiempo);
		// assert
		Assert.assertEquals(500, precio.getValor(), 0);
	}

}
