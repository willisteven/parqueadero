package com.ceiba.parqueadero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.ceiba.parqueadero.models.exception.VigilanteNotFoundException;
import com.ceiba.parqueadero.models.serviceint.VigilanteService;
import com.ceiba.parqueadero.objetosnegocio.VehiculoNegocio;
import com.ceiba.parqueadero.objetosnegocio.VehiculosParqueaderoNegocio;
import com.ceiba.parqueadero.util.RespuestaJson;

import java.rmi.RemoteException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VigilanteRestController {

	@Autowired
	private VigilanteService vigilanteService;

	@PostMapping(path = "/registroVehiculo", consumes = "application/json", produces = "application/json")
	public RespuestaJson realizarIngreso(@RequestBody VehiculoNegocio vehiculo) {
		return vigilanteService.realizarRegistroVehiculo(vehiculo);
	}

	@PostMapping(path = "/salidaVehiculo", consumes = "application/json", produces = "application/json")
	public RespuestaJson realizarSalida(@RequestBody VehiculoNegocio vehiculo) {
		return vigilanteService.realizarSalidaVehiculo(vehiculo);
	}

	@GetMapping(path = "/vehiculosParqueadero", produces = "application/json")
	public List<VehiculosParqueaderoNegocio> vehiculosParqueadero() {
		return vigilanteService.getVehiculosParqueadero();
	}

	@GetMapping(path = "/cilindraje/{placa}", produces = "application/json")
	public VehiculoNegocio cilindrajeMoto(@PathVariable String placa) {
		try {
			return vigilanteService.getCilindrajeMoto(placa);
		} catch (VigilanteNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrado", ex);

		}
	}

	@GetMapping(path = "/trmsuperfinanciera", produces = "application/json")
	public RespuestaJson valorTRM() throws RemoteException {
		return vigilanteService.obtenerTrm();

	}
}
