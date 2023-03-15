package com.mysena.cdsp3.app.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysena.cdsp3.app.entities.Estado;
import com.mysena.cdsp3.app.entities.Pedido;
import com.mysena.cdsp3.app.entities.Usuario;


@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long>{
	
	List<Pedido> findByEstado(Estado estado);
	
	List<Pedido> findByUsuario(Usuario usuario);
}
