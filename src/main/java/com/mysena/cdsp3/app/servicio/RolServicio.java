package com.mysena.cdsp3.app.servicio;

import java.util.List;

import com.mysena.cdsp3.app.entities.Rol;

public interface RolServicio {

	public List<Rol> listarRoles();
    public Rol obtenerId(Integer id);
    public Rol agregar(Rol rol);
    public Rol actualizar(Rol rol);
    public void eliminar(Integer id);
}
