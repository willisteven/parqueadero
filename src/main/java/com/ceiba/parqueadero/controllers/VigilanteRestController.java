package com.ceiba.parqueadero.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero.models.serviceint.IVigilanteService;
import com.ceiba.parqueadero.util.RespuestaJson;

import org.json.simple.JSONObject;

@RestController
@RequestMapping("/api")
public class VigilanteRestController {
	
	@Autowired
	private IVigilanteService vigilanteService;
	
	@PostMapping(path = "/registroVehiculo", consumes = "application/json", produces = "application/json")
	public RespuestaJson realizarIngreso(@RequestBody JSONObject vehiculo){
		
		return vigilanteService.realizarRegistroVehiculo(vehiculo);
}
	
	@PostMapping(path = "/salidaVehiculo", consumes = "application/json", produces = "application/json")
	public RespuestaJson realizarSalida(@RequestBody JSONObject vehiculo){
		return vigilanteService.realizarSalidaVehiculo(vehiculo);
}

	
}

