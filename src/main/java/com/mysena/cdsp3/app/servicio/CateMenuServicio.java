package com.mysena.cdsp3.app.servicio;

import java.util.List;

import com.mysena.cdsp3.app.entities.CateMenu;

public interface CateMenuServicio {

	public List<CateMenu> listarCateMenu();
    public CateMenu obtenerId(Integer id);
    public CateMenu agregar(CateMenu categoria);
    public CateMenu actualizar(CateMenu categoria);
    public void eliminar(Integer id);
}
