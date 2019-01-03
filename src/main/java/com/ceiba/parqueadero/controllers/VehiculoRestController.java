package com.ceiba.parqueadero.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.models.services.IRegistroService;
import com.ceiba.parqueadero.models.services.IVehiculoService;
import com.ceiba.parqueadero.util.RespuestaJson;


@RestController
@RequestMapping("/api")
public class VehiculoRestController {
	
	@Autowired
	private IVehiculoService vehiculoService;
	
	
	@RequestMapping(value = "/registroVehiculo", method = RequestMethod.POST)
	public RespuestaJson permitirIngreso(@RequestBody Vehiculo vehiculo){
		return vehiculoService.realizarRegistroVehiculo(vehiculo);
}

}

