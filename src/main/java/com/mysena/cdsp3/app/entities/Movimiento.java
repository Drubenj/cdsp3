package com.mysena.cdsp3.app.entities;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movimiento")
public class Movimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idmovimiento;
	private String nombre;

	
	public int getIdmovimiento() {
		return idmovimiento;
	}
	public void setIdmovimiento(int idmovimiento) {
		this.idmovimiento = idmovimiento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
