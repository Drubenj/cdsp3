package com.mysena.cdsp3.app.servicioimp;

import java.lang.reflect.Array;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.dto.UsuarioRegistroDTO;
import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.repositorio.UsuarioRepositorio;
import com.mysena.cdsp3.app.servicio.UsuarioServicioDTO;

@Service
public class UsuarioServiciodtoimp{

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	//@Override
	//public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		//Usuario usuario = new Usuario( registroDTO.getIdUsuario(), registroDTO.getNombres(), registroDTO.getApellidos(),
			//	 registroDTO.getDireccion(), registroDTO.getCorreo(), registroDTO.getTelefono(), registroDTO.getContrasenia(),
				// Array.asList(new Rol ("role usuario"));
		
		//return usuarioRepositorio.save(usuario);
	//}
	}

