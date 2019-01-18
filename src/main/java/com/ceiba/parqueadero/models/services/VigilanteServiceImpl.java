package com.ceiba.parqueadero.models.services;

import java.rmi.RemoteException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.models.entity.Registro;
import com.ceiba.parqueadero.models.entity.TipoVehiculo;
import com.ceiba.parqueadero.models.entity.Vehiculo;
import com.ceiba.parqueadero.models.exception.VigilanteInternalServerErrorException;
import com.ceiba.parqueadero.models.exception.VigilanteNotFoundException;
import com.ceiba.parqueadero.models.serviceint.PrecioService;
import com.ceiba.parqueadero.models.serviceint.RegistroService;
import com.ceiba.parqueadero.models.serviceint.TipoVehiculoService;
import com.ceiba.parqueadero.models.serviceint.TrmSuperFinancieraService;
import com.ceiba.parqueadero.models.serviceint.VehiculoService;
import com.ceiba.parqueadero.models.serviceint.VigilanteService;
import com.ceiba.parqueadero.objetosnegocio.VehiculoNegocio;
import com.ceiba.parqueadero.objetosnegocio.VehiculosParqueaderoNegocio;
import com.ceiba.parqueadero.constantes.Constantes;
import com.ceiba.parqueadero.models.entity.Precio;
import com.ceiba.parqueadero.reglas.ReglasParqueadero2;
import com.ceiba.parqueadero.util.RespuestaJson;

@Service
public class VigilanteServiceImpl implements VigilanteService {

	ReglasParqueadero2 reglasParqueadero = new ReglasParqueadero2();

	private static final String PLACA = "placa";

	@Autowired
	private RegistroService registroService;

	@Autowired
	private VehiculoService vehiculoService;

	@Autowired
	private PrecioService precioService;

	@Autowired
	private TipoVehiculoService tipoVehiculoService;

	@Autowired
	private TrmSuperFinancieraService trmService;

	@Override
	public RespuestaJson realizarRegistroVehiculo(VehiculoNegocio vehiculojs) {
		RespuestaJson respuesta = null;
		Vehiculo vehiculo = this.getVehiculoJson(vehiculojs);
		respuesta = this.validacionReglasParqueadero(vehiculo);
		return respuesta;

	}

	@Override
	public RespuestaJson realizarSalidaVehiculo(VehiculoNegocio vehiculojs) {
		RespuestaJson respuesta = null;
		Vehiculo vehiculo = this.getVehiculoJson(vehiculojs);
		respuesta = this.realizarSalida(vehiculo);
		return respuesta;

	}

	@Override
	public List<VehiculosParqueaderoNegocio> getVehiculosParqueadero() {
		List<VehiculosParqueaderoNegocio> listJsonObject = new ArrayList<>();

		List<Registro> listRegistros = registroService.buscarRegistrosVehiculosActivos(Constantes.ACTIVO);
		for (Registro registro : listRegistros) {
			VehiculosParqueaderoNegocio json = new VehiculosParqueaderoNegocio();
			json.setPlaca(registro.getVehiculo().getPlaca());
			json.setTipo(registro.getVehiculo().getIdTipoVehiculo().getTipo());

			String formatoFecha = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(registro.getIngresoFecha());

			json.setIngresoFecha(formatoFecha);
			listJsonObject.add(json);
		}
		return listJsonObject;
	}

	@Override
	public VehiculoNegocio getCilindrajeMoto(String placa) throws VigilanteNotFoundException {
		VehiculoNegocio json = new VehiculoNegocio();
		Vehiculo vehiculo = this.vehiculoService.buscarCilindraje(placa, Constantes.ACTIVO);
		if (vehiculo != null) {
			json.setPlaca(vehiculo.getPlaca());
			json.setCilindraje(vehiculo.getCilindraje());
			json.setTipo(vehiculo.getIdTipoVehiculo().getTipo());

		} else {
			throw new VigilanteNotFoundException("Vehiculo no encontrado");

		}
		return json;
	}

	public Vehiculo getVehiculoJson(VehiculoNegocio vehiculoJs) {
		Vehiculo vehiculo = null;

		String tipo = vehiculoJs.getTipo();
		String placa = vehiculoJs.getPlaca();
		int cilindrajeJson = vehiculoJs.getCilindraje();

		TipoVehiculo tipoVehiculo = this.validarTipoVehiculo(tipo);
		if (tipoVehiculo != null) {
			vehiculo = new Vehiculo(0, placa, tipoVehiculo, cilindrajeJson, Constantes.ACTIVO);
		}
		return vehiculo;
	}

	public TipoVehiculo validarTipoVehiculo(String tipo) {
		return this.tipoVehiculoService.consultarTipoVehiculo(tipo);
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

		if (cantidadHoras > Constantes.HORAS_LIMTE) {
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
		if (vehiculo != null) {
			if (!this.isVehiculoExiste(vehiculo)) {
				if (this.isCupoDisponible(vehiculo)) {
					if (this.isAutorizado(vehiculo)) {
						this.guardarVehiculoRegistro(vehiculo);
					} else {
						return new RespuestaJson(HttpStatus.OK.value(), false, Constantes.NO_AUTORIZADO);
					}
				} else {
					return new RespuestaJson(HttpStatus.OK.value(), false, Constantes.NO_CUPO_DISPONIBLE);
				}
			} else {
				return new RespuestaJson(HttpStatus.OK.value(), false, Constantes.YA_ESTA_PARQUEADERO);
			}
		} else {
			return new RespuestaJson(HttpStatus.OK.value(), false, Constantes.NO_PERMITIDO);

		}
		return new RespuestaJson(HttpStatus.OK.value(), true, Constantes.VEHICULO_INGRESADO);
	}

	public boolean isVehiculoExiste(Vehiculo vehiculo) {
		boolean isVehiculoExiste = false;

		isVehiculoExiste = vehiculoService.vehiculoExiste(vehiculo.getPlaca(), Constantes.ACTIVO);

		return isVehiculoExiste;
	}

	public boolean isCupoDisponible(Vehiculo vehiculo) {
		boolean isCupoDisponible = false;
		List<Vehiculo> listVehiculos = vehiculoService
				.buscarPorTipoVehiculoActivo(vehiculo.getIdTipoVehiculo().getTipo(), Constantes.ACTIVO);
		if (this.reglasParqueadero.disponibilidadVehiculo(listVehiculos.size(),
				vehiculo.getIdTipoVehiculo().getTipo())) {
			isCupoDisponible = true;
		}
		return isCupoDisponible;
	}

	public boolean isAutorizado(Vehiculo vehiculo) {
		boolean isAutorizado = false;
		if (this.reglasParqueadero.validarPlacaLunesDomingos(vehiculo.getPlaca())) {
			isAutorizado = true;
		}
		return isAutorizado;
	}

	public void guardarVehiculoRegistro(Vehiculo vehiculo) {
		Date ingresoFecha = new Date();
		Registro registro = new Registro(0, ingresoFecha, null, Constantes.VALOR_INICIAL, vehiculo);

		vehiculoService.guardarVehiculo(vehiculo);
		registroService.guardarRegistro(registro);

	}

	public RespuestaJson obtenerTrm() throws RemoteException, VigilanteInternalServerErrorException {
		try {
			return this.trmService.obtenerTrm();
		} catch (Exception e) {
			throw new VigilanteInternalServerErrorException(
					"Hubo un error de comunicacion con el web service de la TRM");
		}

	}
}
