package com.ceiba.parqueadero.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_tiempo")
public class TipoTiempo  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoTiempo;

	@Column(name ="tipo" , nullable= false )
	private String tipo;
	
	public TipoTiempo(int idTipoTiempo, String tipo) {
		super();
		this.idTipoTiempo = idTipoTiempo;
		this.tipo = tipo;
	}
	
	public int getIdTipoTiempo() {
		return idTipoTiempo;
	}

	public void setIdTipoTiempo(int idTipoTiempo) {
		this.idTipoTiempo = idTipoTiempo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	

}
