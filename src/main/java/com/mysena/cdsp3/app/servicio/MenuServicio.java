package com.mysena.cdsp3.app.servicio;

import java.util.List;

import com.mysena.cdsp3.app.entities.CateMenu;
import com.mysena.cdsp3.app.entities.Menu;

public interface MenuServicio {

	public List<Menu> listarMenu();
    public Menu obtenerId(Integer id);
    public Menu agregar(Menu menu);
    public Menu actualizar(Menu menu);
    public void eliminar(Integer id);
    public List<Menu> listarMenuPorCategoria(CateMenu categoria);
}
