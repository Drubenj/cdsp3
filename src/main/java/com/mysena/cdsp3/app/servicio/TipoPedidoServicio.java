package com.mysena.cdsp3.app.servicio;

import java.util.List;

import com.mysena.cdsp3.app.entities.TipoPedido;


public interface TipoPedidoServicio {

    public List<TipoPedido> listarTipoPedido();
    public TipoPedido obtenerId(Integer id);
    public TipoPedido agregar(TipoPedido tipo);
    public TipoPedido actualizar(TipoPedido tipo);
    public void eliminar(Integer id);
}
