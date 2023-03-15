package com.mysena.cdsp3.app.servicioimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.entities.CateMenu;
import com.mysena.cdsp3.app.entities.Menu;
import com.mysena.cdsp3.app.repositorio.MenuRepositorio;
import com.mysena.cdsp3.app.servicio.MenuServicio;

@Service
public class MenuServicioimp implements MenuServicio {
	
	@Autowired
	private MenuRepositorio repositorio;
	
	@Override
	public List<Menu> listarMenu() {
		return this.repositorio.findAll();
	}

	@Override
	public Menu obtenerId(Integer id) {
		
		return this.repositorio.findById(id).get();
	}

	@Override
	public Menu agregar(Menu menu) {
		
		return this.repositorio.save(menu);
	}

	@Override
	public Menu actualizar(Menu menu) {
		
		return this.repositorio.save(menu);
	}

	@Override
	public void eliminar(Integer id) {
		this.repositorio.deleteById(id);
		
	}

	@Override
	public List<Menu> listarMenuPorCategoria(CateMenu categoria) {
		
		return repositorio.findByCategoria(categoria);
	}

}
