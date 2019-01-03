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
@Table(name = "precio")
public class Precio  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPrecio;
	
	@ManyToOne()
	@JoinColumn(name = "id_tipo_vehiculo" ,nullable = false)
	private TipoVehiculo idTipoVehiculo; 
	
	@ManyToOne()
	@JoinColumn(name = "id_tipo_tiempo" ,nullable = false)
	private TipoTiempo idTipoTiempo;
	
	@Column(name ="valor" , nullable= false )
	private double valor;

	
	
	public Precio(int idPrecio, TipoVehiculo idTipoVehiculo, TipoTiempo idTipoTiempo, double valor) {
		super();
		this.idPrecio = idPrecio;
		this.idTipoVehiculo = idTipoVehiculo;
		this.idTipoTiempo = idTipoTiempo;
		this.valor = valor;
	}

	public int getIdPrecio() {
		return idPrecio;
	}

	public void setIdPrecio(int idPrecio) {
		this.idPrecio = idPrecio;
	}

	public TipoVehiculo getIdTipoVehiculo() {
		return idTipoVehiculo;
	}

	public void setIdTipoVehiculo(TipoVehiculo idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}

	public TipoTiempo getIdTipoTiempo() {
		return idTipoTiempo;
	}

	public void setIdTipoTiempo(TipoTiempo idTipoTiempo) {
		this.idTipoTiempo = idTipoTiempo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	
}
