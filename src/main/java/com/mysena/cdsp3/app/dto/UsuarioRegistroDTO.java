package com.mysena.cdsp3.app.dto;

import java.util.List;

public class UsuarioRegistroDTO {

	private Long id;
    private Long idUsuario;
    private String nombres;
    private String apellidos;
    private Long telefono;
    private String direccion;
    private String correo;
    private String contrasenia;
    
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
	public UsuarioRegistroDTO(Long id, Long idUsuario, String nombres, String apellidos, Long telefono,
			String direccion, String correo, String contrasenia) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
		this.contrasenia = contrasenia;
	}
	public UsuarioRegistroDTO(Long idUsuario, String nombres, String apellidos, Long telefono, String direccion,
			String correo, String contrasenia) {
		super();
		this.idUsuario = idUsuario;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
		this.contrasenia = contrasenia;
	}
	public UsuarioRegistroDTO(String correo) {
		super();
		this.correo = correo;
	}
	public UsuarioRegistroDTO() {

	}
      
}
