package com.mysena.cdsp3.app.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ventas")
@Data
@Getter
@Setter
public class Venta implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_venta")
    private Long id;
	
    
    @Column(name="total")
    private Double total;
    
    @Column(name="fecha")
    private Date fecha;
    
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuario;

	public Venta() {
	}

	public Venta(Long id, Double total, Date fecha, Pedido pedido, Usuario usuario) {
		this.id = id;
		this.total = total;
		this.fecha = fecha;
		this.pedido = pedido;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Venta [id=" + id + ", total=" + total + ", fecha=" + fecha + ", pedido=" + pedido + ", usuario="
				+ usuario + "]";
	}		
}

