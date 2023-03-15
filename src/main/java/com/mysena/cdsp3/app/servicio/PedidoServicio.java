package com.mysena.cdsp3.app.servicio;

import java.util.List;

import com.mysena.cdsp3.app.entities.Estado;
import com.mysena.cdsp3.app.entities.Pedido;
import com.mysena.cdsp3.app.entities.Usuario;

public interface PedidoServicio {

	public List<Pedido> listarPedidos();
    public Pedido obtenerId(Long id);
    public Pedido agregar(Pedido pedido);
    public Pedido actualizar(Pedido pedido);
    public void eliminar(Long id);
    public Pedido crear(Pedido pedido);
    public List<Pedido> listarPedidoPorEstado(Estado estado);
    public List<Pedido> listarPedidoPorUsuario(Usuario usuario);
    
}
