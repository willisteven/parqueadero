package com.ceiba.parqueadero.reglas;

import java.util.Calendar;
import java.util.Date;

public class ReglasParqueadero2 {
	private Calendar fechaPrestamo;

	public ReglasParqueadero2() {
		fechaPrestamo = Calendar.getInstance();

	}

	public boolean disponibilidadVehiculo(int cantidad, String tipovehiculo) {
		boolean flag = false;

		if (cantidad < 20 && ("carro").equals(tipovehiculo)) {
			flag = true;
		} else if (cantidad < 10 && ("moto").equals(tipovehiculo)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * Metodo que valida los dias permitidos para las placas que comienzan por la
	 * letra A.
	 * 
	 * @param placa
	 * @return
	 */
	public boolean validarPlacaLunesDomingos(String placa) {

		boolean isAutorizado = true;
		String letraUp = placa.toUpperCase().substring(0, 1);
		if (("A").equals(letraUp)) {
			fechaPrestamo.setTime(new Date());
			if (fechaPrestamo.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
					|| fechaPrestamo.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				isAutorizado = true;
			} else {
				isAutorizado = false;
			}
		}
		return isAutorizado;
	}

	public void setCalendar(Calendar c) {
		fechaPrestamo = c;
	}
}
