package com.mysena.cdsp3.app.servicio;

import com.mysena.cdsp3.app.dto.UsuarioRegistroDTO;
import com.mysena.cdsp3.app.entities.Usuario;

public interface UsuarioServicioDTO {

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
}
