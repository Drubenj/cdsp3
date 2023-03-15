package com.mysena.cdsp3.app.repositorio;

import com.mysena.cdsp3.app.entities.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

    public Usuario findBy(Class<Usuario> aClass, Long id);
    
    Optional <Usuario> findByCorreo(String correo);
    
}
