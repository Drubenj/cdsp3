package com.mysena.cdsp3.app.servicioimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.entities.TipoPedido;
import com.mysena.cdsp3.app.repositorio.TipoPedidoRepositorio;
import com.mysena.cdsp3.app.servicio.TipoPedidoServicio;

@Service
public class TipoPedidoServicioimp implements TipoPedidoServicio{

	@Autowired 
	private TipoPedidoRepositorio repositorio;
	@Override
	public List<TipoPedido> listarTipoPedido() {
		
		return this.repositorio.findAll();
	}

	@Override
	public TipoPedido obtenerId(Integer id) {
		
		return this.repositorio.findById(id).get();
	}

	@Override
	public TipoPedido agregar(TipoPedido tipo) {
		
		return this.repositorio.save(tipo);
	}

	@Override
	public TipoPedido actualizar(TipoPedido tipo) {
		
		return this.repositorio.save(tipo);
		
	}

	@Override
	public void eliminar(Integer id) {
		
		this.repositorio.deleteById(id);
		
	}

}
