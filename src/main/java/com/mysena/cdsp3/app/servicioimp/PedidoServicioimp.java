package com.mysena.cdsp3.app.servicioimp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.entities.DetallePedido;
import com.mysena.cdsp3.app.entities.Estado;
import com.mysena.cdsp3.app.entities.Pedido;
import com.mysena.cdsp3.app.entities.TipoPedido;
import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.repositorio.PedidoRepositorio;
import com.mysena.cdsp3.app.servicio.PedidoServicio;

@Service
public class PedidoServicioimp implements PedidoServicio{
	
	Usuario usuario = new Usuario();
	TipoPedido tipo = new TipoPedido();
	Estado estado = new Estado();
	Date Fecha;
	List <DetallePedido> detalles = new ArrayList<DetallePedido>();
	
	
	@Autowired
	private PedidoRepositorio repositorio;
	
	@Override
	public List<Pedido> listarPedidos() {

		return this.repositorio.findAll();
	}

	@Override
	public Pedido obtenerId(Long id) {
		
		return this.repositorio.findById(id).get();
	}

	@Override
	public Pedido agregar(Pedido pedido) {

		return this.repositorio.save(pedido);
	}

	@Override
	public Pedido actualizar(Pedido pedido) {

		return this.repositorio.save(pedido);
	}

	@Override
	public void eliminar(Long id) {
		
		this.repositorio.deleteById(id);
	}

	@Override
	public Pedido crear(Pedido pedido) {
		
		pedido.getUsuario();
		pedido.getTipo();
		pedido.getFecha();
		pedido.getEstado();
		
		this.repositorio.save(pedido);
		
		return pedido;
	}

	@Override
	public List<Pedido> listarPedidoPorEstado(Estado estado) {
		
		return this.repositorio.findByEstado(estado);
	}

	@Override
	public List<Pedido> listarPedidoPorUsuario(Usuario usuario) {
		
		return this.repositorio.findByUsuario(usuario);
	}

}
