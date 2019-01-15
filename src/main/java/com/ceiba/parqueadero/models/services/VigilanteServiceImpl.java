package com.ceiba.parqueadero.models.services;

import java.rmi.RemoteException;

import java.text.SimpleDateFormat;
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
import com.ceiba.parqueadero.models.serviceint.ITrmSuperFinancieraService;
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

	@Autowired
	private ITrmSuperFinancieraService trmService;

	@Override
	public RespuestaJson realizarRegistroVehiculo(JSONObject vehiculojs) {
		RespuestaJson respuesta = null;
		try {
			Vehiculo vehiculo = this.getVehiculoJson(vehiculojs);
			respuesta = this.validacionReglasParqueadero(vehiculo);
		} catch (Exception e) {
			return new RespuestaJson(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, e.getMessage());
		}
		return respuesta;

	}

	@Override
	public RespuestaJson realizarSalidaVehiculo(JSONObject vehiculojs) {
		RespuestaJson respuesta = null;
		try {
			Vehiculo vehiculo = this.getVehiculoJson(vehiculojs);
			respuesta = this.realizarSalida(vehiculo);
		} catch (Exception e) {
			return new RespuestaJson(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, e.getMessage());

		}
		return respuesta;

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

			String formatoFecha = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(registro.getIngresoFecha());

			json.put("ingresoFecha", formatoFecha);

			listJsonObject.add(json);
		}
		return listJsonObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getCilindrajeMoto(String placa) {
		JSONObject json = new JSONObject();
		Vehiculo vehiculo = this.vehiculoService.buscarCilindraje(placa, Constantes.ACTIVO);
		if (vehiculo != null) {
			json.put(PLACA, vehiculo.getPlaca());
			json.put("cilindraje", vehiculo.getCilindraje());
			json.put("tipo", vehiculo.getIdTipoVehiculo().getTipo());

		} else {
			json.put("Mensaje", Constantes.VEHICULO_NO_ESTA_PARQUEADERO);

		}
		return json;
	}

	public Vehiculo getVehiculoJson(JSONObject vehiculoJs) {
		Vehiculo vehiculo = null;

		String tipo = vehiculoJs.get("tipo").toString();
		String placa = vehiculoJs.get(PLACA).toString();
		String cilindrajeJson = vehiculoJs.get("cilindraje").toString();
		int cilindraje = 0;
		cilindraje = Integer.parseInt(cilindrajeJson);

		TipoVehiculo tipoVehiculo = this.validarTipoVehiculo(tipo);
		if (tipoVehiculo != null) {
			vehiculo = new Vehiculo(0, placa, tipoVehiculo, cilindraje, Constantes.ACTIVO);
		}
		return vehiculo;
	}

	public TipoVehiculo validarTipoVehiculo(String tipo) {
		TipoVehiculo tipoVehiculo = this.tipoVehiculoService.consultarTipoVehiculo(tipo);
		if (tipoVehiculo != null) {
			return tipoVehiculo;
		} else {
			return null;
		}
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
			return new RespuestaJson(HttpStatus.OK.value(), true,
					"El vehiculo con placa " + vehiculo.getPlaca() + " ingreso en la fecha: "
							+ registro.getIngresoFecha() + " y el valor del alquiler del parqueadero es: " + costo);

		} else {

			return new RespuestaJson(HttpStatus.OK.value(), false, Constantes.VEHICULO_NO_ESTA_PARQUEADERO);
		}

	}

	public int calcularCobroParqueadero(Vehiculo vehiculo, Date ingresoFecha, Date salidaFecha) {
		long ingresoFechaTime = ingresoFecha.getTime();
		long salidaFechaTime = salidaFecha.getTime();

		Precio precioHora = this.precioService.obtenerPrecioPorTipoVehiculoYTiempo(
				vehiculo.getIdTipoVehiculo().getIdTipoVehiculo(), Constantes.ID_HORA);
		Precio precioDia = this.precioService.obtenerPrecioPorTipoVehiculoYTiempo(
				vehiculo.getIdTipoVehiculo().getIdTipoVehiculo(), Constantes.ID_DIA);

		int cantidadHoras = 1;
		cantidadHoras += calcularTiempoEnElParqueadero(ingresoFechaTime, salidaFechaTime, Constantes.VALOR_HORA);
		int cantidadDias = calcularTiempoEnElParqueadero(ingresoFechaTime, salidaFechaTime, Constantes.VALOR_DIA);

		cantidadHoras -= (cantidadDias * 24);

		if (cantidadHoras > 8) {
			cantidadHoras = 0;
			cantidadDias++;
		}

		int valorPorHoras = (int) (cantidadHoras * precioHora.getValor());
		int valorPorDias = (int) (cantidadDias * precioDia.getValor());
		int costo = valorPorHoras + valorPorDias;

		if ("moto".equals(vehiculo.getIdTipoVehiculo().getTipo())) {
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

	public RespuestaJson validacionReglasParqueadero(Vehiculo vehiculo) {
		boolean yaEstaParqueadero = false;
		boolean cupoDisponible = false;
		boolean autorizado = false;
		boolean isVehiculoExiste = false;

		if (vehiculo != null) {
			isVehiculoExiste = vehiculoService.vehiculoExiste(vehiculo.getPlaca(), Constantes.ACTIVO);
		} else {
			return new RespuestaJson(HttpStatus.OK.value(), false, Constantes.NO_PERMITIDO);
		}

		if (!isVehiculoExiste) {
			yaEstaParqueadero = true;
			List<Vehiculo> listVehiculos = vehiculoService
					.buscarPorTipoVehiculoActivo(vehiculo.getIdTipoVehiculo().getTipo(), Constantes.ACTIVO);
			if (this.reglasParqueadero.disponibilidadVehiculo(listVehiculos.size(),
					vehiculo.getIdTipoVehiculo().getTipo())) {
				cupoDisponible = true;
			}
			if (this.reglasParqueadero.validarPlacaLunesDomingos(vehiculo.getPlaca())) {
				autorizado = true;
			}
		}

		if (!yaEstaParqueadero) {
			return new RespuestaJson(HttpStatus.OK.value(), false, Constantes.YA_ESTA_PARQUEADERO);
		}

		if (!cupoDisponible) {
			return new RespuestaJson(HttpStatus.OK.value(), false, Constantes.NO_CUPO_DISPONIBLE);
		}

		if (!autorizado) {
			return new RespuestaJson(HttpStatus.OK.value(), false, Constantes.NO_AUTORIZADO);
		}
		this.guardarVehiculoRegistro(vehiculo);

		return new RespuestaJson(HttpStatus.OK.value(), false, Constantes.VEHICULO_INGRESADO);
	}

	public void guardarVehiculoRegistro(Vehiculo vehiculo) {
		Date ingresoFecha = new Date();
		Registro registro = new Registro(0, ingresoFecha, null, 0, vehiculo);

		vehiculoService.guardarVehiculo(vehiculo);
		registroService.guardarRegistro(registro);

	}

	public RespuestaJson obtenerTrm() throws RemoteException {
		return this.trmService.obtenerTrm();
	}

}
