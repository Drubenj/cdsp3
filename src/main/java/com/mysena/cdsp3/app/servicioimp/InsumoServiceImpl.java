package com.mysena.cdsp3.app.servicioimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.entities.Insumo;
import com.mysena.cdsp3.app.repositorio.InsumoRepository;
import com.mysena.cdsp3.app.servicio.InsumoService;

@Service
public class InsumoServiceImpl implements InsumoService{

	@Autowired
	private InsumoRepository repository;
	
	@Override
	public List<Insumo> listarTodosLosInsumos() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Insumo guardarInsumos(Insumo insum) {
		// TODO Auto-generated method stub
		return repository.save(insum);
	}

	@Override
	public Insumo obtenerInsumoPorId(Integer idinsumo) {
		// TODO Auto-generated method stub
		return repository.findById(idinsumo).get();
	}

	@Override
	public Insumo actualizarInsumo(Insumo insumo) {
		// TODO Auto-generated method stub
		return repository.save(insumo);
	}

	@Override
	public void eliminarInsumo(Integer idinsumo) {
		// TODO Auto-generated method stub
		repository.deleteById(idinsumo);
	}
}
