package com.mysena.cdsp3.app.servicioimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.entities.CateMenu;
import com.mysena.cdsp3.app.repositorio.CateMenuRepositorio;
import com.mysena.cdsp3.app.servicio.CateMenuServicio;

@Service
public class CateMenuServicioimp implements CateMenuServicio{
	
	@Autowired
	private CateMenuRepositorio repositorio;

	@Override
	public List<CateMenu> listarCateMenu() {
		
		return this.repositorio.findAll();
	}

	@Override
	public CateMenu obtenerId(Integer id) {
	
		return this.repositorio.findById(id).get();
	}

	@Override
	public CateMenu agregar(CateMenu categoria) {
		
		return this.repositorio.save(categoria);
	}

	@Override
	public CateMenu actualizar(CateMenu categoria) {
		
		return this.repositorio.save(categoria);
	}

	@Override
	public void eliminar(Integer id) {

		this.repositorio.deleteById(id);
	}


}
