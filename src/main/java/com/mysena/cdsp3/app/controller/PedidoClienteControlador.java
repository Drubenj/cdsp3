package com.mysena.cdsp3.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysena.cdsp3.app.entities.CateMenu;
import com.mysena.cdsp3.app.entities.DetallePedido;
import com.mysena.cdsp3.app.entities.Domicilio;
import com.mysena.cdsp3.app.entities.Estado;
import com.mysena.cdsp3.app.entities.Menu;
import com.mysena.cdsp3.app.entities.Pedido;
import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.entities.Venta;
import com.mysena.cdsp3.app.servicio.DomicilioServicio;
import com.mysena.cdsp3.app.servicioimp.CateMenuServicioimp;
import com.mysena.cdsp3.app.servicioimp.DetallePedidoServicioimp;
import com.mysena.cdsp3.app.servicioimp.EstadoServicioimp;
import com.mysena.cdsp3.app.servicioimp.MenuServicioimp;
import com.mysena.cdsp3.app.servicioimp.PedidoServicioimp;
import com.mysena.cdsp3.app.servicioimp.TipoPedidoServicioimp;
import com.mysena.cdsp3.app.servicioimp.UsuarioServicioimp;
import com.mysena.cdsp3.app.servicioimp.VentaServicioimp;

@Controller
@RequestMapping(path="/cdsp/cliente")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST })
public class PedidoClienteControlador {
	
	private final Logger log = LoggerFactory.getLogger(PedidoControlador.class);
	
	@Autowired
	private DomicilioServicio domicilioServicio;
	Domicilio domicilio= new Domicilio();
	
	@Autowired
	private MenuServicioimp menuServicio;
	private List<Menu> listaMenu;
	
	@Autowired
	private CateMenuServicioimp catemenuServicio;
	private List<CateMenu> listaCateMenu;
	
	@Autowired
	private DetallePedidoServicioimp detalleServicio;
	private List<DetallePedido> listaDetalles = new ArrayList<DetallePedido>();
	
	@Autowired
	private PedidoServicioimp pedidoServicio;
	private Pedido pedido = new Pedido();
	private List<Pedido> listaMisPedidos;
	
	@Autowired
	private VentaServicioimp ventaServicio;
	private Venta venta = new Venta();
	private List<Venta> listaMisCompras;
	
	@Autowired
	private EstadoServicioimp estadoServicio;
	private List<Estado> listaEstado;
	
	@Autowired
	private TipoPedidoServicioimp tipoServicio;
	
	@Autowired
	private UsuarioServicioimp usuarioServicio;
	
	
	@GetMapping("/")
	public String inicioCliente() {
		return "cliente/bienvenida2";
	}
	@GetMapping("/menu")
	public String menu(Model modelo) {
		
		this.listaCateMenu = this.catemenuServicio.listarCateMenu();
		this.listaMenu = this.menuServicio.listarMenu();
		
		modelo.addAttribute("listaMenu", this.listaMenu);
		modelo.addAttribute("listaCateMenu", this.listaCateMenu);
		return "cliente/menu";
	}
	@GetMapping("/menu/{id}")
	public String verMenu(@PathVariable ("id")Integer id , Model modelo){
		
		Menu menu = this.menuServicio.obtenerId(id);
		
		modelo.addAttribute("menu", menu);
		return "cliente/verMenu";
	}
	@PostMapping("/detalle")
    public String agregarDetalle(@RequestParam Integer id, @RequestParam Integer cantidad, HttpSession sesion, Model modelo) {
		DetallePedido detalle = new DetallePedido();
		Menu menu = new Menu();
		Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
		double sumaTotal = 0;
		Menu menuOptional = this.menuServicio.obtenerId(id);
		
		menu = menuOptional;
		detalle.setCantidad(cantidad);
		detalle.setMenu(menu);
		detalle.setSubtotal(menu.getPrecio()*cantidad);
		
		Integer idMenu = menu.getId();
		boolean insertado = listaDetalles.stream().anyMatch(m->m.getMenu().getId() == idMenu);
		
		if(!insertado) {
			listaDetalles.add(detalle);
		}
		sumaTotal = listaDetalles.stream().mapToDouble(dt->dt.getSubtotal()).sum();
			
		venta.setTotal(sumaTotal);
   	 
		modelo.addAttribute("domicilio", this.domicilio);
	modelo.addAttribute("usuario", usuario);
   	 modelo.addAttribute("pedido", this.pedido);
   	 modelo.addAttribute("listaDetalles", this.listaDetalles);
   	 modelo.addAttribute("venta", venta);
  
   	 return "cliente/detallePedido";
	
	}
	//Quitar detalle
    @GetMapping("/eliminar/detalle/{id}")
    public String quitarMenu(@PathVariable Integer id, HttpSession sesion, Model modelo) {
   	 
    Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
   	 //Nueva lista de menus
   	 List<DetallePedido> detallesNuevos = new ArrayList<DetallePedido>();
   	 
   	 for(DetallePedido detallePedido: listaDetalles) {
   		 
   		 if(detallePedido.getMenu().getId()!= id) {
   			 detallesNuevos.add(detallePedido);
   			 
   		 }
   	 }
   	 //lista con los menus restantes (sin eliminar)
   	 listaDetalles = detallesNuevos;
   	 
   	 double sumaTotal = 0;
   	 sumaTotal = listaDetalles.stream().mapToDouble(dt->dt.getSubtotal()).sum();
   	 
   	 venta.setTotal(sumaTotal);

   	 // 
   	 modelo.addAttribute("pedido", this.pedido);
   	 modelo.addAttribute("listaDetalles", this.listaDetalles);
   	 modelo.addAttribute("venta", venta);
   	modelo.addAttribute("usuario", usuario);
   	 return "cliente/detallePedido";
   	 
    }
    @GetMapping("/verDetalle")
    public String verDetalle(Model modelo, HttpSession sesion) {
   	 
    Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
    
    
   	 modelo.addAttribute("pedido", this.pedido);
   	 modelo.addAttribute("domicilio", this.domicilio);
   	 modelo.addAttribute("listaDetalles", this.listaDetalles);
   	 modelo.addAttribute("venta", venta);
   	modelo.addAttribute("usuario", usuario);
   	 return "/cliente/detallePedido";
    }
    @PostMapping("/pedido/guardar")
    public String guardarPedido(@ModelAttribute ("domicilio")Domicilio domicilio, HttpSession sesion) {
   	Date fechaCreacion = new Date();
   	Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
   	
   	
   	this.pedido.setFecha(fechaCreacion);
   	this.pedido.setEstado(this.estadoServicio.obtenerEstado(1));
   	this.pedido.setTipo(this.tipoServicio.obtenerId(2));
   	this.pedido.setUsuario(usuario); // AQUI   OBTENER EL USUSARIO CON LA SESION
   	
   	//Guardar pedido
   this.pedidoServicio.agregar(pedido);
   	Pedido pedidoed = this.pedidoServicio.obtenerId(pedido.getId());
   	
   	//Guardar detalle
   	for(DetallePedido dt : listaDetalles) {
   		dt.setPedido(pedido);
   		detalleServicio.agregar(dt);
   	}
   	
   	domicilio.setPedido(pedidoed);
   	domicilio.setUsuario(pedidoed.getUsuario());
   	domicilio.setEstado(this.estadoServicio.obtenerEstado(1));
   	
  this.domicilioServicio.guardarDomicilio(domicilio);
	 log.info("@@@Domicilio pedido{}", pedidoed.getId());
	 log.info("@@@Domicilio usuario{}", pedidoed.getUsuario().getId());
	 
   	
   	
   	//domicilio = new Domicilio();
   //	pedido = new Pedido();
   	//listaDetalles.clear();
   	venta= new Venta();
   	return "redirect:/cdsp/cliente/menu";
    }
    
    
    
    @GetMapping("/buscar/estado/{id}")
    public String buscarPedidoEstado(@PathVariable ("id") Integer id, Model modelo) {
   	 log.info("id categoria {}", id);
   	 
   	 this.listaEstado = this.estadoServicio.listarTodosLosEstados();
   	 Estado estado = this.estadoServicio.obtenerEstado(id);
   	 List <Pedido> pedidosEstado = this.pedidoServicio.listarPedidoPorEstado(estado);
   	 
   	 log.info("Lista Pedidos {}", pedidosEstado);
   	 
   	 modelo.addAttribute("listaEstado", this.listaEstado); 
   	 modelo.addAttribute("estado", estado); 
   	 modelo.addAttribute("listaPedidosEstado", pedidosEstado);
   	 return "cliente/pedidos";
    }
    
    @GetMapping("/pedidos/estadisticas")
    public String estadisticas( Model modelo) {
    
    return "cliente/estadisticas";
    }
    
    @GetMapping("/mispedidos")
    public String misPedidos(Model modelo, HttpSession sesion) {
    	
    	Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
    	
    	this.listaMisPedidos = this.pedidoServicio.listarPedidoPorUsuario(usuario);

    	modelo.addAttribute("listaMisPedidos", this.listaMisPedidos);
    	
    	return "cliente/pedidos";
    }
    
    @GetMapping("/miscompras")
    public String misCompras(Model modelo, HttpSession sesion) {
    	
    	Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
    	
    	this.listaMisCompras = this.ventaServicio.listarVentasPorUsuario(usuario);
    	
    	log.info("@@@@@@  usuario {}", usuario.getId());
    	modelo.addAttribute("listaMisCompras", this.listaMisCompras);
    	
    	return "cliente/ventas";
    }
    
    @GetMapping("/detalle/{id}")
    public String verDetalleCompra(@PathVariable("id") Long id, Model modelo) {
    	
    	Venta venta = new Venta ();
    	
    	venta = this.ventaServicio.obtenerId(id);
    	List <DetallePedido> detalles = venta.getPedido().getDetalles();
    	
    	modelo.addAttribute("venta", venta);
    	modelo.addAttribute("detalles", detalles);
    	
    	return "cliente/consultarDetalleVenta";
    }
		
}


