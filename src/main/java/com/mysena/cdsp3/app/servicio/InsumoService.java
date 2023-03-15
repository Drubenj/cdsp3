package com.mysena.cdsp3.app.servicio;

import java.util.List;

import com.mysena.cdsp3.app.entities.Insumo;

public interface InsumoService {

	public List<Insumo> listarTodosLosInsumos();
	public Insumo guardarInsumos(Insumo insum);
	public Insumo obtenerInsumoPorId(Integer idinsumo);
	public Insumo actualizarInsumo(Insumo insumo);
	public void eliminarInsumo(Integer idinsumo);
}
