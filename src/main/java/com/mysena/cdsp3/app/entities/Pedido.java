package com.mysena.cdsp3.app.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="pedidos")
@Data
@Getter
@Setter
public class Pedido implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
    private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha")
    private Date fecha;
    
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="tipo")
	private TipoPedido tipo;
	
	@ManyToOne
	@JoinColumn(name="estado")
	private Estado estado;

	@OneToMany (mappedBy="pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List <DetallePedido> detalles = new ArrayList<DetallePedido>();
	
	public Pedido() {
	}

	public Pedido(Long id, Date fecha, Usuario usuario, TipoPedido tipo, Estado estado) {
		this.id = id;
		this.fecha = fecha;
		this.usuario = usuario;
		this.tipo = tipo;
		this.estado = estado;
	}
	
	public Pedido(Date fecha, Usuario usuario, TipoPedido tipo, Estado estado) {
		this.fecha = fecha;
		this.usuario = usuario;
		this.tipo = tipo;
		this.estado = estado;
		this.detalles = detalles;
	}

	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoPedido getTipo() {
		return tipo;
	}

	public void setTipo(TipoPedido tipo) {
		this.tipo = tipo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fecha=" + fecha + ", usuario=" + usuario + ", tipo=" + tipo + ", estado="
				+ estado + ", detalles=" + detalles + "]";
	}



	
}
