package com.mysena.cdsp3.app.servicio;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


import com.mysena.cdsp3.app.entities.Pedido;
import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.entities.Venta;
import com.mysena.cdsp3.app.entities.VistaVentasMensuales;


public interface VentaServicio {

    public List<Venta> listarVentas();
    public Venta obtenerId(Long id);
    public Venta agregar(Venta usuario);
    public Venta actualizar(Venta usuario);
    public void eliminar(Long id);
    public List<Venta> listarVentasPorUsuario(Usuario usuario);
    public boolean ventaConPedido(Pedido pedido);
    public List<Venta> ultimasVentas(int limite);
    Double acumuladoVentasMes (Date fecha);
    public List<Venta> ventasPorFecha(Date fechaInicio, Date fechaFin);
    
}
