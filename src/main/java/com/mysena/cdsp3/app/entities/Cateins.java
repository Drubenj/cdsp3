package com.mysena.cdsp3.app.entities;

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
@Table(name = "cateins")
public class Cateins {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcateins;
	private String nombre;
	public int getIdcateins() {
		return idcateins;
	}
	public void setIdcateins(int idcateins) {
		this.idcateins = idcateins;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	
}
