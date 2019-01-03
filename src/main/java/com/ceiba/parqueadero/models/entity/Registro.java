package com.ceiba.parqueadero.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "regisro")
public class Registro  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRegistro;

	@Column(name ="ingresoFecha" , nullable= false )
	@Temporal(TemporalType.DATE)
	private Date ingresoFecha;
	
	@Column(name ="salidaFecha" , nullable= true )
	@Temporal(TemporalType.DATE)
	private Date salidaFecha;
	
	@Column(name ="valorPagar" , nullable= true )
	private double valorPagar;
	
	@Column(name = "id_vehiculo", insertable = false, updatable = false)
	private int idVehiculo;

	@ManyToOne()
	@JoinColumn(name = "ID_VEHICULO")
	private Vehiculo vehiculo;
	
	public Registro(int idRegistro, Date ingresoFecha, Date salidaFecha, double valorPagar, int idVehiculo,
			Vehiculo vehiculo) {
		super();
		this.idRegistro = idRegistro;
		this.ingresoFecha = ingresoFecha;
		this.salidaFecha = salidaFecha;
		this.valorPagar = valorPagar;
		this.idVehiculo = idVehiculo;
		this.vehiculo = vehiculo;
	}
	
	public int getId() {
		return idRegistro;
	}

	public void setId(int idRegistro) {
		this.idRegistro = idRegistro;
	}

	public Date getIngresoFecha() {
		return ingresoFecha;
	}

	public void setIngresoFecha(Date ingresoFecha) {
		this.ingresoFecha = ingresoFecha;
	}

	public Date getSalidaFecha() {
		return salidaFecha;
	}

	public void setSalidaFecha(Date salidaFecha) {
		this.salidaFecha = salidaFecha;
	}

	public double getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(double valorPagar) {
		this.valorPagar = valorPagar;
	}

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
}
