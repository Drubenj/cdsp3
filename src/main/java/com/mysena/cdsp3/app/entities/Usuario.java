/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mysena.cdsp3.app.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="usuarios")
@Data
@Getter
@Setter
public class Usuario implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private Long id;
    
	
    @Column(name="id_usuario")
    private Long idUsuario;
    
    @Column(name="nombres")
    private String nombres;
    
    @Column(name="apellidos")
    private String apellidos;
    
    @Column(name="telefono")
    private Long telefono;
    
    @Column(name="direccion")
    private String direccion;
    
    @Column(name="correo")
    private String correo;
    
    @Column(name="contraseña")
    private String contrasenia;
    
    @OneToMany(mappedBy="usuario")
    private List<Pedido> pedidos = new ArrayList<Pedido>();
    
    @OneToMany(mappedBy="usuario")
    private List<Venta> ventas = new ArrayList<Venta>();
 
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_roles",
				joinColumns = @JoinColumn (name = "fk_id_usuario", referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn (name = "fk_id_rol", referencedColumnName =  "id")
			)
		private List<Rol> listaRoles;
    
	public Usuario() {

	}
	

	public Usuario(long idUsuario, String nombres, String apellidos, long telefono, String direccion, String correo,
			String contrasenia) {
		super();
		this.idUsuario = idUsuario;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
		this.contrasenia = contrasenia;
	}


	public Usuario(Long id, Long idUsuario, String nombres, String apellidos, Long telefono, String direccion,
			String correo, String contrasenia, List<Pedido> pedidos, Rol rol) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.pedidos = pedidos;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}


	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", idUsuario=" + idUsuario + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", telefono=" + telefono + ", direccion=" + direccion + ", correo=" + correo + ", contrasenia="
				+ contrasenia + "]";
	}   
}
