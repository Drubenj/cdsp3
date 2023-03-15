package com.mysena.cdsp3.app.servicio;

import java.util.List;

import com.mysena.cdsp3.app.entities.DetallePedido;


public interface DetallePedidoServicio {

	public List<DetallePedido> listarDetallePedidos();
    public DetallePedido obtenerId(Long id);
    public DetallePedido agregar(DetallePedido detalle);
    public DetallePedido actualizar(DetallePedido detalle);
    public void eliminar(Long id);
}
