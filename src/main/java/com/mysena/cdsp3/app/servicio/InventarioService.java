package com.mysena.cdsp3.app.servicio;

import java.util.List;

import com.mysena.cdsp3.app.entities.Inventario;

public interface InventarioService {

	public List<Inventario> listarTodosLosInventarios();
	public Inventario guardarInventario(Inventario inventari);
	public Inventario obtenerInventarioPorId(Integer idinventario);
	public Inventario actualizarInventario(Inventario inventario);
	public void eliminarInventario(Integer idinventario);
}
