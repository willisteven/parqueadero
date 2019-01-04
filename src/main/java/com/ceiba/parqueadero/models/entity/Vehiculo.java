package com.ceiba.parqueadero.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVehiculo;

	@Column(name ="placa" , nullable= false )
	private String placa;
	
	@ManyToOne()
	@JoinColumn(name = "id_tipo_vehiculo" ,nullable = false)
	private TipoVehiculo idTipoVehiculo;
	
	@Column(name = "cilindraje" , nullable= true)
	private int cilindraje;
	
	@Column(name = "Activo")
	private int activo;

	public Vehiculo(int idVehiculo, String placa, TipoVehiculo idTipoVehiculo, int cilindraje) {
		super();
		this.idVehiculo = idVehiculo;
		this.placa = placa;
		this.idTipoVehiculo = idTipoVehiculo;
		this.cilindraje = cilindraje;
	}
	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public TipoVehiculo getIdTipoVehiculo() {
		return idTipoVehiculo;
	}

	public void setIdTipoVehiculo(TipoVehiculo idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}



}
