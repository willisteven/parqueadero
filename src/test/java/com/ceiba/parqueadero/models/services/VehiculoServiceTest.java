package com.ceiba.parqueadero.models.services;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ceiba.parqueadero.constantes.Constantes;
import com.ceiba.parqueadero.models.dao.IVehiculoDao;
import com.ceiba.parqueadero.models.entity.Precio;
import com.ceiba.parqueadero.models.entity.TipoVehiculo;
import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.testdatabuilder.PrecioTestDataBuilder;
import com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

public class VehiculoServiceTest {

	@Mock
	private IVehiculoDao vehiculoDao;

	@InjectMocks
	private VehiculoServiceImpl vehiculoService;

	@org.junit.Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testBuscarCilindraje() {
		// arrange
		VehiculoTestDataBuilder vehiculoTest = new VehiculoTestDataBuilder();
		vehiculoTest.withIdTipoVehiculo(new TipoVehiculo("moto", "Tipo de vehiculo moto"));
		vehiculoTest.withCilindraje(600);
		Vehiculo vehiculo = vehiculoTest.build();
		when(this.vehiculoDao.findByPlacaAndTipoVehiculo(vehiculo.getPlaca(), vehiculo.getActivo()))
				.thenReturn(vehiculo);

		// act
		Vehiculo vehiculoResult = vehiculoService.buscarCilindraje(vehiculo.getPlaca(), vehiculo.getActivo());
		// assert
		Assert.assertEquals(600, vehiculoResult.getCilindraje());
	}

}
