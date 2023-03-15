package com.mysena.cdsp3.app.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "insumo")
public class Insumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idinsumo;
	private String nombre;
	private int precio;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado")
	private Estado estado;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cateins")
	private Cateins cateins;

	public Insumo() {
		super();
	}

	public Insumo(int idinsumo, String nombre, int precio, Estado estado, Cateins cateins) {
		super();
		this.idinsumo = idinsumo;
		this.nombre = nombre;
		this.precio = precio;
		this.estado = estado;
		this.cateins = cateins;
	}

	public int getIdinsumo() {
		return idinsumo;
	}

	public void setIdinsumo(int idinsumo) {
		this.idinsumo = idinsumo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cateins getCateins() {
		return cateins;
	}

	public void setCateins(Cateins cateins) {
		this.cateins = cateins;
	}

	
	
	
	@Override
	public String toString() {
		return "Insumo [idinsumo=" + idinsumo + ", nombre=" + nombre + ", precio=" + precio + ", estado=" + estado
				+ ", cateins=" + cateins + "]";
	}

	
	
}
