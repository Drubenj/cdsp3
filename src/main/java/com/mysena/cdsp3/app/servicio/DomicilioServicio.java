package com.mysena.cdsp3.app.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.entities.Domicilio;

@Service
public interface DomicilioServicio {

public List<Domicilio> listarTodosLosDomicilios(); 	
public Domicilio guardarDomicilio(Domicilio domicilio);
public Domicilio obtenerDomicilioPorId(Long id);
public Domicilio actualizarDomicilio(Domicilio domicilio);
public void eliminarDomicilio(Long id);
}
