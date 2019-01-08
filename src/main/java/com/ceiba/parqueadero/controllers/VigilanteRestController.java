package com.ceiba.parqueadero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero.models.serviceint.IVigilanteService;
import com.ceiba.parqueadero.util.RespuestaJson;

import java.util.List;

import org.json.simple.JSONObject;

@RestController
@RequestMapping("/api")
public class VigilanteRestController {

	@Autowired
	private IVigilanteService vigilanteService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/registroVehiculo", consumes = "application/json", produces = "application/json")
	public RespuestaJson realizarIngreso(@RequestBody JSONObject vehiculo) {
		return vigilanteService.realizarRegistroVehiculo(vehiculo);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/salidaVehiculo", consumes = "application/json", produces = "application/json")
	public RespuestaJson realizarSalida(@RequestBody JSONObject vehiculo) {
		return vigilanteService.realizarSalidaVehiculo(vehiculo);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/vehiculosParqueadero", produces = "application/json")
	public List<JSONObject> vehiculosParqueadero() {
		return vigilanteService.getVehiculosParqueadero();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/cilindraje/{placa}", produces = "application/json")
	public JSONObject cilindrajeMoto(@PathVariable String placa) {
		return vigilanteService.getCilindrajeMoto(placa);
	}
}
