package com.ceiba.parqueadero.reglas;

import com.ceiba.parqueadero.models.entity.Vehiculo;

public class reglasParqueadero {

	/**
	 * Metodo que valida el tipo de vehiculo que es permitido en el parqueadero
	 * @param vehiculo
	 * @return
	 */
	public boolean permiteTipoVehiculo(Vehiculo vehiculo) {
		boolean permite = false;
		if (vehiculo.getIdTipoVehiculo().getTipo().equals("carro") || vehiculo.getIdTipoVehiculo().getTipo().equals("moto"))
			{permite = true;}
		else {
			permite = false;}
		return permite;
		
	}
	
	public boolean disponibilidadVehiculo(int cantidad, String tipovehiculo) {
		boolean flag = false;

		if (cantidad < 20 && tipovehiculo.equals("carro")) {
			flag = true;
		} else if (cantidad < 10 && tipovehiculo.equals("moto")) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
	
	
	
}
