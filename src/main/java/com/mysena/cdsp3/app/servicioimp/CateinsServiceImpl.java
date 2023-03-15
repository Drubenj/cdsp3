package com.mysena.cdsp3.app.servicioimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.entities.Cateins;
import com.mysena.cdsp3.app.repositorio.CateinsRepository;
import com.mysena.cdsp3.app.servicio.CateinsService;

@Service
public class CateinsServiceImpl implements CateinsService{

	@Autowired
	private CateinsRepository repository;

	@Override
	public List<Cateins> listaCategoria() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
}
