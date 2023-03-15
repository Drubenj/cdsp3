package com.mysena.cdsp3.app.entities;

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
@Table(name="domicilios")
@Data
@Getter
@Setter

public class Domicilio {
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)

private Long id;

@Column(name="ciudad")
private String ciudad;

@Column(name="direccion")
private String direccion;

@Column(name="barrio")
private String barrio;

@Column(name="inmueble")
private String inmueble;

@Column(name="interior")
private String interior;


@ManyToOne
@JoinColumn (name="usuario")
private Usuario usuario;

@OneToOne
@JoinColumn (name="pedido")
private Pedido pedido;

@ManyToOne
@JoinColumn (name="estado")
private Estado estado;

public Domicilio(Long id, String ciudad, String direccion, String barrio, String inmueble, String interior,
		Usuario usuario, Pedido pedido) {
	super();
	this.id = id;
	this.ciudad = ciudad;
	this.direccion = direccion;
	this.barrio = barrio;
	this.inmueble = inmueble;
	this.interior = interior;
	this.usuario = usuario;
	this.pedido = pedido;
}
public Domicilio(long id, String ciudad, String direccion, String barrio, String inmueble, String interior) {
	super();
	this.id = id;
	this.ciudad = ciudad;
	this.direccion = direccion;
	this.barrio = barrio;
	this.inmueble = inmueble;
	this.interior = interior;
}
public Domicilio(String ciudad, String direccion, String barrio, String inmueble, String interior) {
	super();
	this.ciudad = ciudad;
	this.direccion = direccion;
	this.barrio = barrio;
	this.inmueble = inmueble;
	this.interior = interior;
}
public Domicilio() {
	super();
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getCiudad() {
	return ciudad;
}
public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}
public String getBarrio() {
	return barrio;
}
public void setBarrio(String barrio) {
	this.barrio = barrio;
}
public String getInmueble() {
	return inmueble;
}
public void setInmueble(String inmueble) {
	this.inmueble = inmueble;
}
public String getInterior() {
	return interior;
}
public void setInterior(String interior) {
	this.interior = interior;
}

public Usuario getUsuario() {
	return usuario;
}
public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}
public Pedido getPedido() {
	return pedido;
}
public void setPedido(Pedido pedido) {
	this.pedido = pedido;
}

public Estado getEstado() {
	return estado;
}
public void setEstado(Estado estado) {
	this.estado = estado;
}
@Override
public String toString() {
	return "Domicilio [id=" + id + ", ciudad=" + ciudad + ", direccion=" + direccion + ", barrio=" + barrio
			+ ", inmueble=" + inmueble + ", interior=" + interior + ", usuario=" + usuario
			+ ", pedido=" + pedido + "]";
}

}
