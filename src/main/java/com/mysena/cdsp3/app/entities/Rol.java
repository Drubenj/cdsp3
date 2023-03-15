package com.mysena.cdsp3.app.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="roles")
@Data
public class Rol implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@ManyToMany(mappedBy = "listaRoles", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List <Usuario> listaUsuarios;
	
	public Rol() {
	
	}
	

	public Rol(Integer id) {
		super();
		this.id = id;
	}


	public Rol(String nombre) {
		super();
		this.nombre = nombre;
	}

	
	public Rol(Integer id, String nombre, List<Usuario> listaUsuarios) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.listaUsuarios = listaUsuarios;
	}


	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

	@Override
	public String toString() {
		return "Rol [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
}