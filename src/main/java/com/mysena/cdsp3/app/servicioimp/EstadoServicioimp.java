package com.mysena.cdsp3.app.servicioimp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.entities.Estado;
import com.mysena.cdsp3.app.repositorio.EstadoRepositorio;
import com.mysena.cdsp3.app.servicio.EstadoServicio;

@Service
public class EstadoServicioimp implements EstadoServicio{

	@Autowired
	private EstadoRepositorio repositorio;
	
	@Override
	public Estado obtenerEstado(Integer id) {
		
		return this.repositorio.findById(id).get();
	}

	@Override
	public List<Estado> listarTodosLosEstados() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public Estado guardarEstado(Estado estad) {
		// TODO Auto-generated method stub
		return repositorio.save(estad);
	}


	@Override
	public Estado actualizarEstado(Estado estado) {
		// TODO Auto-generated method stub
		return repositorio.save(estado);
	}

	@Override
	public void eliminarEstado(Integer ID_Estado) {
		// TODO Auto-generated method stub
		repositorio.deleteById(ID_Estado);
	}

	@Override
	public List<Estado> listarEstadosPedidos() {
		
		List<Estado> estados = new ArrayList<Estado>();
		
		estados.add(this.obtenerEstado(1));
		estados.add(this.obtenerEstado(2));
		estados.add(this.obtenerEstado(3));
		estados.add(this.obtenerEstado(6));
		estados.add(this.obtenerEstado(9));
		return estados;
	}

	@Override
	public List<Estado> listarEstadosPedidosCocina() {
		
		List<Estado> estados = new ArrayList<Estado>();
		
		estados.add(this.obtenerEstado(1));
		estados.add(this.obtenerEstado(2));
		estados.add(this.obtenerEstado(3));
		
		return estados;
	}



}
