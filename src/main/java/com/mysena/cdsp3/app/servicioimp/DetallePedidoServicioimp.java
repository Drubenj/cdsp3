package com.mysena.cdsp3.app.servicioimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.entities.DetallePedido;
import com.mysena.cdsp3.app.repositorio.DetallePedidoRepositorio;
import com.mysena.cdsp3.app.servicio.DetallePedidoServicio;

@Service
public class DetallePedidoServicioimp implements DetallePedidoServicio{

	@Autowired
	private DetallePedidoRepositorio repositorio;
	
	@Override
	public List<DetallePedido> listarDetallePedidos() {
		
		return this.repositorio.findAll();
	}

	@Override
	public DetallePedido obtenerId(Long id) {
		
		return this.repositorio.findById(id).get();
	}

	@Override
	public DetallePedido agregar(DetallePedido detalle) {
	
		return this.repositorio.save(detalle);
	}

	@Override
	public DetallePedido actualizar(DetallePedido detalle) {
		
		return this.repositorio.save(detalle);
	}

	@Override
	public void eliminar(Long id) {
		
		this.repositorio.deleteById(id);;
	}

}
