package com.mysena.cdsp3.app;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.mysena.cdsp3.app.entities.Estado;
import com.mysena.cdsp3.app.entities.Pedido;
import com.mysena.cdsp3.app.entities.TipoPedido;
import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.servicio.PedidoServicio;


@DataJpaTest
public class PedidoTest {
	
	@Autowired
	private PedidoServicio pedidoServicio;
	
	
	@Test
	public void testGuardarPedido() {
		Date fecha = new Date();
		Usuario user1 = new Usuario(1001065562, "Julian", "Captuallo", 7722893, "calle 14 b", "stiventijo2003@gmail.com","juliCap1002");
		Estado estado = new Estado("Pendiente");
		TipoPedido tipo = new TipoPedido("Mesa");
		Pedido pedido = new Pedido(fecha,user1,tipo, estado);
		this.pedidoServicio.crear(pedido);
	};
}
