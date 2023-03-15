package com.mysena.cdsp3.app.servicioimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.entities.Movimiento;
import com.mysena.cdsp3.app.repositorio.MovimientoRepository;
import com.mysena.cdsp3.app.servicio.MovimientoService;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
	private MovimientoRepository repository;
	
	@Override
	public List<Movimiento> listaMovimiento() {
		return repository.findAll();
	}

	@Override
	public Movimiento crear(Movimiento movimiento) {
		// TODO Auto-generated method stub
		return this.repository.save(movimiento);
	}
	
}
