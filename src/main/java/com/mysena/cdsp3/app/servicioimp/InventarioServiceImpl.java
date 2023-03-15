package com.mysena.cdsp3.app.servicioimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.entities.Inventario;
import com.mysena.cdsp3.app.repositorio.InventarioRepository;
import com.mysena.cdsp3.app.servicio.InventarioService;

@Service
public class InventarioServiceImpl implements InventarioService {

	@Autowired
	private InventarioRepository repository;
	
	@Override
	public List<Inventario> listarTodosLosInventarios() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Inventario guardarInventario(Inventario inventari) {
		// TODO Auto-generated method stub
		return repository.save(inventari);
	}

	@Override
	public Inventario obtenerInventarioPorId(Integer idinventario) {
		// TODO Auto-generated method stub
		return repository.findById(idinventario).get();
	}

	@Override
	public Inventario actualizarInventario(Inventario inventario) {
		// TODO Auto-generated method stub
		return repository.save(inventario);
	}

	@Override
	public void eliminarInventario(Integer idinventario) {
		// TODO Auto-generated method stub
		repository.deleteById(idinventario);
	}

	
}
