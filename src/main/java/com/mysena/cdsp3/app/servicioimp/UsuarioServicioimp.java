/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mysena.cdsp3.app.servicioimp;


import com.mysena.cdsp3.app.entities.Rol;
import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.repositorio.UsuarioRepositorio;
import com.mysena.cdsp3.app.servicio.UsuarioServicio;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicioimp implements UsuarioServicio {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Override
    public List<Usuario> listarTodos() {
        return this.usuarioRepositorio.findAll();
    }

    @Override
    public Usuario agregar(Usuario usuario) {
        return this.usuarioRepositorio.save(usuario);
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
      return this.usuarioRepositorio.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        this.usuarioRepositorio.deleteById(id);
    }


    @Override
    public Usuario obtenerId(Long id) {
        return this.usuarioRepositorio.findById(id).get();
    }

	@Override
	public Optional<Usuario> obtenerPorCorreo(String correo) {
		
		return this.usuarioRepositorio.findByCorreo(correo);
	}

	@Override
	public List<Usuario> listarClientes(Rol rol) {
		
		List<Usuario> usuarios = this.usuarioRepositorio.findAll();
		List<Usuario> listaClientes = new ArrayList<Usuario>();
		for(Usuario user: usuarios) {
			if (user.getListaRoles().contains(rol)) {
				listaClientes.add(user);
			}
		}
		return listaClientes;
	}


		/*
	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByCorreo(correo);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password invalidos");
		}
		return new User(usuario.getCorreo(),usuario.getContrasenia(), mapearAutoridadesRoles(usuario.getListaRoles()));
		
	}
	
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles (Collection<Rol> roles){
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
    */
}
