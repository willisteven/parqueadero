package com.ceiba.parqueadero.models.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.models.entity.Registro;
import com.ceiba.parqueadero.models.entity.TipoVehiculo;
import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.models.serviceint.IPrecioService;
import com.ceiba.parqueadero.models.serviceint.IRegistroService;
import com.ceiba.parqueadero.models.serviceint.ITipoVehiculoService;
import com.ceiba.parqueadero.models.serviceint.IVehiculoService;
import com.ceiba.parqueadero.models.serviceint.IVigilanteService;
import com.ceiba.parqueadero.models.entity.Precio;
import com.ceiba.parqueadero.reglas.ReglasParqueadero;
import com.ceiba.parqueadero.util.RespuestaJson;

@Service
public class VigilanteServiceImpl implements IVigilanteService {

	ReglasParqueadero reglasParqueadero = new ReglasParqueadero();
	public static final int ESTADO = 1;

	@Autowired
	private IRegistroService registroService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IPrecioService precioService;

	@Autowired
	private ITipoVehiculoService tipoVehiculoService;

	@Override
	public RespuestaJson realizarRegistroVehiculo(JSONObject vehiculojs) {
		try {
			Vehiculo vehiculo;
			vehiculo = this.createVehiculoFromJson(vehiculojs);

			List<Vehiculo> listVehiculos = vehiculoService
					.buscarPorTipoVehiculoActivo(vehiculo.getIdTipoVehiculo().getTipo(), ESTADO);
			if (this.reglasParqueadero.permiteTipoVehiculo(vehiculo)) {
				if (this.reglasParqueadero.disponibilidadVehiculo(listVehiculos.size(),
						vehiculo.getIdTipoVehiculo().getTipo())) {
					if (this.reglasParqueadero.validarPlacaLunesDomingos(vehiculo.getPlaca())) {
						Date ingresoFecha = new Date();
						Registro registro = new Registro(ingresoFecha, null, 0, vehiculo);
						vehiculoService.guardarVehiculo(vehiculo);
						registroService.guardarRegistro(registro);
					} else {
						return new RespuestaJson(200, "No esta autorizado a ingresar");
					}
				} else {
					return new RespuestaJson(200, "No hay cupo Disponible");
				}

			} else {
				return new RespuestaJson(200, "Este vehiculo no esta permitido");

			}
		} catch (Exception e) {
			return new RespuestaJson(500, "Se produjo un error en el servicio");
		}
		return new RespuestaJson(200, "Vehiculo Ingresado correctamente");
	}

	protected Vehiculo createVehiculoFromJson(JSONObject vehiculoJs) {
		Vehiculo vehiculo = null;

		String tipo = vehiculoJs.get("tipo").toString();
		String placa = vehiculoJs.get("placa").toString();
		int cilindraje = (int) vehiculoJs.get("cilindraje");

		TipoVehiculo tipoVehiculo;
		tipoVehiculo = this.tipoVehiculoService.consultarTipoVehiculo(tipo);

		vehiculo = new Vehiculo(placa, tipoVehiculo, cilindraje);

		return vehiculo;
	}

	@Override
	public RespuestaJson realizarSalidaVehiculo(JSONObject vehiculojs) {
		Date salidaFecha = new Date();
		Vehiculo vehiculo;
		vehiculo = this.createVehiculoFromJson(vehiculojs);

		this.realizarSalida(salidaFecha, vehiculo);
		return null;

	}

	public RespuestaJson realizarSalida(Date salidaFecha, Vehiculo vehiculo) {
		Registro registro = this.registroService.buscarVehiculoPorPlaca(vehiculo.getPlaca());
		if (registro != null) {

			this.calcularCobroParqueadero(vehiculo, registro.getIngresoFecha(), salidaFecha);
		} else {

			return new RespuestaJson(200, "El vehiculo no se encuentra activo, o no ha ingresado al parqueadero.");
		}
		return null;
	}

	public double calcularCobroParqueadero(Vehiculo vehiculo, Date ingresoFecha, Date salidaFecha) {
		long ingresoFechaTime = ingresoFecha.getTime();
		long salidaFechaTime = salidaFecha.getTime();

		Precio precioHora = this.precioService
				.obtenerPrecioPorTipoVehiculoYTiempo(vehiculo.getIdTipoVehiculo().getIdTipoVehiculo(), 1);
		Precio precioDia = this.precioService
				.obtenerPrecioPorTipoVehiculoYTiempo(vehiculo.getIdTipoVehiculo().getIdTipoVehiculo(), 2);

		int cantidadHoras = 1;
		cantidadHoras += calcularTiempoEnElParqueadero(ingresoFechaTime, salidaFechaTime, 3600000);
		int cantidadDias = calcularTiempoEnElParqueadero(ingresoFechaTime, salidaFechaTime, 86400000);

		cantidadHoras -= (cantidadDias * 24);

		if (cantidadHoras > 8) {
			cantidadHoras = 0;
			cantidadDias++;
		}

		int valorPorHoras = (int) (cantidadHoras * precioHora.getValor());
		int valorPorDias = (int) (cantidadDias * precioDia.getValor());
		int costo = valorPorHoras + valorPorDias;

		if (vehiculo.getIdTipoVehiculo().getTipo() == "moto") {
			calcularCostoAdicionalCilindraje(vehiculo.getCilindraje());
		}

		return costo;

	}

	public int calcularTiempoEnElParqueadero(long fechaEntrada, long fechaSalida, int tiempo) {
		long resta = fechaSalida - fechaEntrada;
		return (int) resta / tiempo;
	}

	public int calcularCostoAdicionalCilindraje(int cilindraje) {
		return (cilindraje > 500) ? 2000 : 0;
	}

	@Override
	public List<Vehiculo> obtenerVehiculosDelParqueadero() {
		List<Vehiculo> listVehiculos= new ArrayList<>();
		Vehiculo vehiculo= null;
		listVehiculos.add(vehiculo);
		return listVehiculos;
	}

	@Override
	public RespuestaJson obtenerTRM() {
		return null;
	}

}
