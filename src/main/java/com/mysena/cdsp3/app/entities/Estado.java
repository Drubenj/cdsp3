package com.mysena.cdsp3.app.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="estado")
@Data
@Getter
@Setter
public class Estado implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private Integer id;
	
	@Column(name="nombre")
    private String nombre;

	public Integer getId() {
		return id;
	}
	
	@OneToMany(mappedBy="estado")
	private List<Pedido> pedidos;

	public Estado(String nombre) {
		this.nombre = nombre;
	}
	public Estado(Integer id, String nombre, List<Pedido> pedidos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pedidos = pedidos;
	}
	public Estado() {
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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
}
