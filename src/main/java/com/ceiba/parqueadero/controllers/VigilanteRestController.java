package com.ceiba.parqueadero.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero.models.dto.VehiculoDto;
import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.models.serviceInt.IVigilanteService;
import com.ceiba.parqueadero.util.RespuestaJson;


@RestController
@RequestMapping("/api")
public class VigilanteRestController {
	
	@Autowired
	private IVigilanteService vigilanteService;
	
	@PostMapping(path = "/registroVehiculo", consumes = "application/json", produces = "application/json")
	public RespuestaJson realizarIngreso(@RequestBody Vehiculo vehiculo){
		
		return vigilanteService.realizarRegistroVehiculo(vehiculo);
}
	
	@PostMapping(path = "/salidaVehiculo", consumes = "application/json", produces = "application/json")
	public RespuestaJson realizarSalida(@RequestBody Vehiculo vehiculo){
		return vigilanteService.realizarSalidaVehiculo(vehiculo);
}

	
}

