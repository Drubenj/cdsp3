package com.mysena.cdsp3.app.servicioimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.entities.Rol;
import com.mysena.cdsp3.app.repositorio.RolRepositorio;
import com.mysena.cdsp3.app.servicio.RolServicio;

@Service
public class RolServicioimp implements RolServicio{

	@Autowired
	private RolRepositorio repositorio;
	
	@Override
	public List<Rol> listarRoles() {
		
		return this.repositorio.findAll();
	}

	@Override
	public Rol obtenerId(Integer id) {
		
		return this.repositorio.findById(id).get();
	}

	@Override
	public Rol agregar(Rol rol) {
		
		return this.repositorio.save(rol);
	}

	@Override
	public Rol actualizar(Rol rol) {
		
		return this.repositorio.save(rol);
	}

	@Override
	public void eliminar(Integer id) {
		
		this.repositorio.deleteById(id);
		
	}

}
