package com.mysena.cdsp3.app.servicio;

import java.util.List;

import com.mysena.cdsp3.app.entities.Movimiento;

public interface MovimientoService {

	public abstract List<Movimiento> listaMovimiento();
	
	public Movimiento crear(Movimiento movimiento);
}
