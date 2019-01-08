package com.ceiba.parqueadero.models.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.models.entity.Registro;
import com.ceiba.parqueadero.models.entity.TipoVehiculo;
import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.models.serviceint.IPrecioService;
import com.ceiba.parqueadero.models.serviceint.IRegistroService;
import com.ceiba.parqueadero.models.serviceint.ITipoVehiculoService;
import com.ceiba.parqueadero.models.serviceint.IVehiculoService;
import com.ceiba.parqueadero.models.serviceint.IVigilanteService;
import com.ceiba.parqueadero.constantes.Constantes;
import com.ceiba.parqueadero.models.entity.Precio;
import com.ceiba.parqueadero.reglas.ReglasParqueadero2;
import com.ceiba.parqueadero.util.RespuestaJson;

@Service
public class VigilanteServiceImpl implements IVigilanteService {

	ReglasParqueadero2 reglasParqueadero = new ReglasParqueadero2();
	
	private static final String PLACA = "placa";


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
			Vehiculo vehiculo = this.getVehiculoJson(vehiculojs);
			if (vehiculo != null) {

				boolean isVehiculoExiste = vehiculoService.vehiculoExiste(vehiculo.getPlaca(), Constantes.ACTIVO);

				if (!isVehiculoExiste) {
					List<Vehiculo> listVehiculos = vehiculoService
							.buscarPorTipoVehiculoActivo(vehiculo.getIdTipoVehiculo().getTipo(), Constantes.ACTIVO);
					if (this.reglasParqueadero.disponibilidadVehiculo(listVehiculos.size(),
							vehiculo.getIdTipoVehiculo().getTipo())) {
						if (this.reglasParqueadero.validarPlacaLunesDomingos(vehiculo.getPlaca())) {
							Date ingresoFecha = new Date();
							Registro registro = new Registro(ingresoFecha, null, 0, vehiculo);
							vehiculoService.guardarVehiculo(vehiculo);
							registroService.guardarRegistro(registro);
						} else {
							return new RespuestaJson(HttpStatus.OK.value(), Constantes.NO_AUTORIZADO);
						}
					} else {
						return new RespuestaJson(HttpStatus.OK.value(), Constantes.NO_CUPO_DISPONIBLE);
					}

				} else {
					return new RespuestaJson(HttpStatus.OK.value(), Constantes.YA_ESTA_PARQUEADERO);
				}
			} else {
				return new RespuestaJson(HttpStatus.OK.value(), Constantes.NO_PERMITIDO);
			}
		} catch (Exception e) {
			return new RespuestaJson(HttpStatus.INTERNAL_SERVER_ERROR.value(), Constantes.ERROR_SERVICIO);
		}
		return new RespuestaJson(HttpStatus.OK.value(), Constantes.VEHICULO_INGRESADO);
	}

	@Override
	public RespuestaJson realizarSalidaVehiculo(JSONObject vehiculojs) {
		Vehiculo vehiculo = this.getVehiculoJson(vehiculojs);
		return this.realizarSalida(vehiculo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JSONObject> getVehiculosParqueadero() {
		List<JSONObject> listJsonObject = new ArrayList<>();
		List<Registro> listRegistros = registroService.buscarRegistrosVehiculosActivos(Constantes.ACTIVO);

		for (Registro registro : listRegistros) {
			JSONObject json = new JSONObject();
			json.put(PLACA, registro.getVehiculo().getPlaca());
			json.put("tipo", registro.getVehiculo().getIdTipoVehiculo().getTipo());
			json.put("ingresoFecha", registro.getIngresoFecha());

			listJsonObject.add(json);
		}
		return listJsonObject;
	}


	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getCilindrajeMoto(String placa) {
		JSONObject json = new JSONObject();
		Vehiculo vehiculo = this.vehiculoService.buscarCilindraje(placa,Constantes.ACTIVO);
		if (vehiculo != null) {
			json.put(PLACA, vehiculo.getPlaca());
			json.put("cilindraje", vehiculo.getCilindraje());
			json.put("tipo", vehiculo.getIdTipoVehiculo().getTipo());
			
		} else {
			json.put("Mensaje",Constantes.VEHICULO_NO_ESTA_PARQUEADERO);

		}
		return json;

	}



	protected Vehiculo getVehiculoJson(JSONObject vehiculoJs) {
		Vehiculo vehiculo = null;

		String tipo = vehiculoJs.get("tipo").toString();
		String placa = vehiculoJs.get(PLACA).toString();
		String cilindrajeJson=vehiculoJs.get("cilindraje").toString();
		int cilindraje = 0;
		if (cilindrajeJson != ("")) {
			cilindraje = Integer.parseInt(cilindrajeJson);
		}
		TipoVehiculo tipoVehiculo = this.tipoVehiculoService.consultarTipoVehiculo(tipo);
		if (tipoVehiculo != null) {
			vehiculo = new Vehiculo(placa, tipoVehiculo, cilindraje, Constantes.ACTIVO);
		} else {
			return null;
		}
		return vehiculo;
	}

	public RespuestaJson realizarSalida(Vehiculo vehiculo) {
		Date salidaFecha = new Date();
		Registro registro = this.registroService.buscarVehiculoPorPlaca(vehiculo.getPlaca());
		if (registro != null) {

			int costo = this.calcularCobroParqueadero(vehiculo, registro.getIngresoFecha(), salidaFecha);
			registro.setSalidaFecha(salidaFecha);
			registro.setValorPagar(costo);
			registro.getVehiculo().setActivo(Constantes.INACTIVO);

			this.registroService.guardarRegistro(registro);
			return new RespuestaJson(HttpStatus.OK.value(),
					"El valor a pagar por la estadia en el parqueadero para el vehiculo con placa "
							+ vehiculo.getPlaca() + " es : $" + costo + " fecha de ingreso : "
							+ registro.getIngresoFecha());

		} else {

			return new RespuestaJson(HttpStatus.OK.value(),
					Constantes.VEHICULO_NO_ESTA_PARQUEADERO);
		}

	}

	public int calcularCobroParqueadero(Vehiculo vehiculo, Date ingresoFecha, Date salidaFecha) {
		long ingresoFechaTime = ingresoFecha.getTime();
		long salidaFechaTime = salidaFecha.getTime();

		Precio precioHora = this.precioService
				.obtenerPrecioPorTipoVehiculoYTiempo(vehiculo.getIdTipoVehiculo().getIdTipoVehiculo(), 1);
		Precio precioDia = this.precioService
				.obtenerPrecioPorTipoVehiculoYTiempo(vehiculo.getIdTipoVehiculo().getIdTipoVehiculo(), 2);

		int cantidadHoras = 1;
		cantidadHoras += calcularTiempoEnElParqueadero(ingresoFechaTime, salidaFechaTime, Constantes.HORA);
		int cantidadDias = calcularTiempoEnElParqueadero(ingresoFechaTime, salidaFechaTime, Constantes.DIA);

		cantidadHoras -= (cantidadDias * 24);

		if (cantidadHoras > 8) {
			cantidadHoras = 0;
			cantidadDias++;
		}

		int valorPorHoras = (int) (cantidadHoras * precioHora.getValor());
		int valorPorDias = (int) (cantidadDias * precioDia.getValor());
		int costo = valorPorHoras + valorPorDias;

		if (vehiculo.getIdTipoVehiculo().getTipo().equals("moto")) {
			costo += calcularCostoAdicionalCilindraje(vehiculo.getCilindraje());
		}

		return costo;

	}

	public int calcularTiempoEnElParqueadero(long fechaEntrada, long fechaSalida, int tiempo) {
		long resta = fechaSalida - fechaEntrada;
		return (int) resta / tiempo;
	}

	public int calcularCostoAdicionalCilindraje(int cilindraje) {
		return (cilindraje > Constantes.CILINDRAJE_TOPE) ? Constantes.VALOR_CILINDRAJE_EXTRA : 0;
	}

}
