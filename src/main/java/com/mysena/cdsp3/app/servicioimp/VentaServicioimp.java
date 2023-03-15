package com.mysena.cdsp3.app.servicioimp;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.controller.PedidoControlador;
import com.mysena.cdsp3.app.entities.Pedido;
import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.entities.Venta;
import com.mysena.cdsp3.app.entities.VistaVentasMensuales;
import com.mysena.cdsp3.app.repositorio.VentaRepositorio;
import com.mysena.cdsp3.app.servicio.VentaServicio;
@Service
public class VentaServicioimp implements VentaServicio{

	private final Logger log = LoggerFactory.getLogger(VentaServicio.class);
	
	@Autowired
	private VentaRepositorio repositorio;
	@Override
	public List<Venta> listarVentas() {
		
		return this.repositorio.findAll();
	}

	@Override
	public Venta obtenerId(Long id) {
		
		return this.repositorio.findById(id).get();
	}

	@Override
	public Venta agregar(Venta venta) {
		
		return this.repositorio.save(venta);
	}

	@Override
	public Venta actualizar(Venta venta) {
	
		return this.repositorio.save(venta);
	}

	@Override
	public void eliminar(Long id) {
		
		this.repositorio.deleteById(id);;
		
	}

	@Override
	public List<Venta> listarVentasPorUsuario(Usuario usuario) {
		
		return this.repositorio.findByUsuario(usuario);
		
	}


	@Override
	public boolean ventaConPedido(Pedido pedido) {
		
		Venta venta = this.repositorio.findByPedido(pedido);
		
		if (venta == null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public List<Venta> ultimasVentas(int limite) {
		
		return this.repositorio.ultimasVentasPorFecha(limite);
	}

	@Override
	public Double acumuladoVentasMes(Date fecha) {
		// TODO Auto-generated method stub
		return this.repositorio.ventaPorMes(fecha);
	}

	@Override
	public List<Venta> ventasPorFecha(Date fechaInicio, Date fechaFin) {
		// TODO Auto-generated method stub
		return this.repositorio.findByFechaBetween(fechaInicio, fechaFin);
	}





}
