package com.mysena.cdsp3.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysena.cdsp3.app.entities.CateMenu;
import com.mysena.cdsp3.app.entities.DetallePedido;
import com.mysena.cdsp3.app.entities.Estado;
import com.mysena.cdsp3.app.entities.Menu;
import com.mysena.cdsp3.app.entities.Pedido;
import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.servicioimp.CateMenuServicioimp;
import com.mysena.cdsp3.app.servicioimp.DetallePedidoServicioimp;
import com.mysena.cdsp3.app.servicioimp.EstadoServicioimp;
import com.mysena.cdsp3.app.servicioimp.MenuServicioimp;
import com.mysena.cdsp3.app.servicioimp.PedidoServicioimp;
import com.mysena.cdsp3.app.servicioimp.UsuarioServicioimp;

@Controller
@RequestMapping(path="/cdsp/cocina")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST })
public class PedidoJefecocinaControlador {
	
	 @Autowired
     private MenuServicioimp menuServicio;  
     private List<Menu> listaMenu;
     
	 @Autowired
     private EstadoServicioimp estadoServicio;
	 private List<Estado> listaEstado = new ArrayList<Estado>();
	 
	 @Autowired
     private CateMenuServicioimp categoriaServicio; 
	 private List<CateMenu> listaCateMenu;
	 
	 @Autowired
     private UsuarioServicioimp usuarioServicio; 

	 @Autowired
     private PedidoServicioimp pedidoServicio;
     private List<Pedido> listaPedidos;
     
     @Autowired
     private DetallePedidoServicioimp detallePedidoServicio;
     private List<DetallePedido> listadetalles;
     
     @Autowired
     private DetallePedidoServicioimp detalleServicio;
     private List<DetallePedido> listaDetalles = new ArrayList<DetallePedido>();

     @GetMapping("/pedidos")
     public String listarPedidosCocina(Model modelo, HttpSession sesion){
    	 
    	 Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
    	 this.listaEstado = this.estadoServicio.listarEstadosPedidosCocina();
    	 this.listaPedidos = this.pedidoServicio.listarPedidoPorEstado(this.estadoServicio.obtenerEstado(1));
    	 
    	 modelo.addAttribute("usuario", usuario);
    	 modelo.addAttribute("listaPedidos", this.listaPedidos);
    	 modelo.addAttribute("listaEstados", this.listaEstado);
    	 return "jefecocina/pedidos";
     }
     
     @GetMapping("/pedidos/estado/{id}")
     public String listarPedidosPorEstado(Model modelo, @PathVariable ("id") Integer id, HttpSession sesion) {
    	 
    	 Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
    	 Estado estado = this.estadoServicio.obtenerEstado(id);
    	 List <Pedido> pedidosEstado = this.pedidoServicio.listarPedidoPorEstado(estado);
    	 this.listaEstado = this.estadoServicio.listarEstadosPedidosCocina();
    	 
    	 modelo.addAttribute("listaEstados", this.listaEstado); 
    	 modelo.addAttribute("estado", estado); 
    	 modelo.addAttribute("listaPedidosEstado", pedidosEstado);
    	 modelo.addAttribute("usuario", usuario);
    	 return "jefecocina/pedidos";
    	
     }
     
     @GetMapping("/pedido/{id}")
     public String consultarPedido(Model modelo, @PathVariable ("id") Long id, HttpSession sesion) {
    	 
    	 Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
    	 Pedido pedido = this.pedidoServicio.obtenerId(id);
    	 List<DetallePedido> detalles = pedido.getDetalles();
    	 
    	 
    	 int confirmarEstadop;
    	 int confirmarEstadoe;
    	 if(pedido.getEstado().getId() == 1) {

    		 confirmarEstadop = 1;
    		 modelo.addAttribute("confirmacionEstadop", confirmarEstadop); 
    		 
    	 } else if (pedido.getEstado().getId() == 2) {
    		 
    		 confirmarEstadoe = 2;
    		 modelo.addAttribute("confirmacionEstadoe", confirmarEstadoe);
    		 
    	 }else {
    		 
    	 }
    	 
    	 modelo.addAttribute("usuario", usuario);
    	 modelo.addAttribute("pedido", pedido);
    	 modelo.addAttribute("listaDetalles", detalles);
    	 return "jefecocina/detallePedido";
    	
     }
     
     @GetMapping("/pedido/actualizar/estado/{id}")
     public String actualizarEstadoPedido(@PathVariable("id") Long id, Model modelo) {
    	 
    	 Pedido pedidoEstado = new Pedido();
    	 pedidoEstado = this.pedidoServicio.obtenerId(id);
    	 int estado = pedidoEstado.getEstado().getId();
    	 if	(estado == 1) {
    	 pedidoEstado.setEstado(this.estadoServicio.obtenerEstado(2));
    	 
    	 }else if(estado == 2) {
    	 pedidoEstado.setEstado(this.estadoServicio.obtenerEstado(3)); 
    	 }else{
    		 
    	 }
    	 this.pedidoServicio.actualizar(pedidoEstado);
    	 
    	 return "redirect:/cdsp/cocina/pedido/{id} (id = ${pedidoEstado.id})}";
    	
     }
}
