package com.mysena.cdsp3.app.servicioimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.entities.Domicilio;
import com.mysena.cdsp3.app.repositorio.DomicilioRepositorio;
import com.mysena.cdsp3.app.servicio.DomicilioServicio;

@Service
public class DomicilioServiciosImpl implements DomicilioServicio {

	@Autowired
	private DomicilioRepositorio repositorio; 
	@Override
	public List<Domicilio> listarTodosLosDomicilios() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public Domicilio guardarDomicilio(Domicilio domicilio) {
		// TODO Auto-generated method stub
		return repositorio.save(domicilio);
	}

	@Override
	public Domicilio obtenerDomicilioPorId(Long id) {
		// TODO Auto-generated method stub
		return repositorio.findById(id).get();
	}

	@Override
	public Domicilio actualizarDomicilio(Domicilio domicilio) {
		// TODO Auto-generated method stub
		return repositorio.save(domicilio);
	}

	@Override
	public void eliminarDomicilio(Long id) {
		// TODO Auto-generated method stub
		repositorio.deleteById(id);
		
	}

}
