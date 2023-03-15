package com.mysena.cdsp3.app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="detalle_pedido")
@Data
@Getter
@Setter
public class DetallePedido implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_detalle_pedido")
    private Long id;
	
	@Column(name="cantidad")
    private Integer cantidad;
	
	@Column(name="subtotal")
    private Double subtotal;
	
	@OneToOne
	@JoinColumn(name="pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="menu")
	private Menu menu;
	
	public DetallePedido() {
	}
	
	public DetallePedido(Integer cantidad, Double subtotal, Pedido pedido, Menu menu) {
		super();
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.pedido = pedido;
		this.menu = menu;
	}

	public DetallePedido(Long id, Integer cantidad, Double subtotal, Pedido pedido, Menu menu) {
		this.id = id;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.pedido = pedido;
		this.menu = menu;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		return "DetallePedido [id=" + id + ", cantidad=" + cantidad + ", subtotal=" + subtotal + ", pedido=" + pedido
				+ ", menu=" + menu + "]";
	}

	
}
