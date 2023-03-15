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
@Table(name="tipo_pedido")
@Data
@Getter
@Setter
public class TipoPedido implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo")
    private Integer id;
	
	@Column(name="nombre")
    private String nombre;
	
	@OneToMany(mappedBy="tipo")
	private List<Pedido> pedidos;

	public TipoPedido() {
	}

	public TipoPedido(Integer id, String nombre, List<Pedido> pedidos) {
		this.id = id;
		this.nombre = nombre;
		this.pedidos = pedidos;
	}
	
	
	public TipoPedido(String nombre) {
		super();
		this.nombre = nombre;
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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "TipoPedido [id=" + id + ", nombre=" + nombre + ", pedidos=" + pedidos + "]";
	}
	
}
