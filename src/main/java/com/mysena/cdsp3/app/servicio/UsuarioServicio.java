package com.mysena.cdsp3.app.servicio;

import com.mysena.cdsp3.app.entities.Rol;
import com.mysena.cdsp3.app.entities.Usuario;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioServicio {
    
	//extends UserDetailsService
    public List<Usuario> listarTodos();
    public Usuario obtenerId(Long id);
    public Usuario agregar(Usuario usuario);
    public Usuario actualizar(Usuario usuario);
    public void eliminar(Long id);
    public Optional <Usuario> obtenerPorCorreo(String correo);
    public List<Usuario> listarClientes(Rol rol);
    
}
