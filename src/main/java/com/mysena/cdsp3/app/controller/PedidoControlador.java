package com.mysena.cdsp3.app.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lowagie.text.DocumentException;
import com.mysena.cdsp3.app.entities.CateMenu;
import com.mysena.cdsp3.app.entities.DetallePedido;
import com.mysena.cdsp3.app.entities.Estado;
import com.mysena.cdsp3.app.entities.Menu;
import com.mysena.cdsp3.app.entities.Pedido;
import com.mysena.cdsp3.app.entities.TipoPedido;
import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.entities.Venta;
import com.mysena.cdsp3.app.entities.VistaVentaMenuMes;
import com.mysena.cdsp3.app.servicioimp.CateMenuServicioimp;
import com.mysena.cdsp3.app.servicioimp.DetallePedidoServicioimp;
import com.mysena.cdsp3.app.servicioimp.EmailServiceImpl;
import com.mysena.cdsp3.app.servicioimp.EstadoServicioimp;
import com.mysena.cdsp3.app.servicioimp.MenuServicioimp;
import com.mysena.cdsp3.app.servicioimp.PedidoServicioimp;
import com.mysena.cdsp3.app.servicioimp.RolServicioimp;
import com.mysena.cdsp3.app.servicioimp.TipoPedidoServicioimp;
import com.mysena.cdsp3.app.servicioimp.UsuarioServicioimp;
import com.mysena.cdsp3.app.servicioimp.VentaServicioimp;
import com.mysena.cdsp3.app.servicioimp.VistaVentasServicioimp;
import com.mysena.cdsp3.app.utilidades.reportes.VentaExporterEXCEL;
import com.mysena.cdsp3.app.utilidades.reportes.VentaExporterPDF;


@Controller
@RequestMapping(path="/cdsp/pedidos")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST })
public class PedidoControlador {
	
	private final Logger log = LoggerFactory.getLogger(PedidoControlador.class);
	

	@Autowired 
	private EmailServiceImpl emailService;
	
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
     private TipoPedidoServicioimp tipoServicio;
     private List<TipoPedido> listaTipo;
     
     @Autowired
     private UsuarioServicioimp usuarioServicio;
     private List<Usuario> listaUsuarios;
     
     @Autowired
     private VentaServicioimp ventaServicio;
     private List<Venta> listaVentas;
     
     @Autowired
     private RolServicioimp rolServivio;
     
     @Autowired
     private PedidoServicioimp pedidoServicio;
     private List<Pedido> listaPedidos;
     
     @Autowired
     private DetallePedidoServicioimp detalleServicio;
     private List<DetallePedido> listaDetalles = new ArrayList<DetallePedido>();
     private List <DetallePedido> listaDetallesEditar = new ArrayList<DetallePedido>();
     
     @Autowired
 	private VistaVentasServicioimp ventaMenuServicio;
     
     Pedido pedido = new Pedido();
     Venta venta= new Venta();
     Usuario user= null;
     
     @GetMapping("")
     public String pedidos(Model modelo,HttpSession sesion) {
    	 
    	 Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
    	 this.listaPedidos = this.pedidoServicio.listarPedidos();
    	 
    	 this.listaEstado= this.estadoServicio.listarEstadosPedidos();
    	 modelo.addAttribute("listaEstado", this.listaEstado); 
    	 modelo.addAttribute("listaPedidos", this.listaPedidos);
    	 modelo.addAttribute("usuario", usuario);
    	 return "pedidosventas/pedidos";
     }
     @GetMapping("/menu")
     public String pedidoMenu(Model modelo){
    	 this.listaMenu = this.menuServicio.listarMenu(); 
    	 this.listaCateMenu=this.categoriaServicio.listarCateMenu();
    	 
    	 modelo.addAttribute("listaCateMenu", this.listaCateMenu);
         modelo.addAttribute("listaMenu", this.listaMenu);
         return "pedidosventas/menuPedido";
     }
     @GetMapping("/menu/{id}")
     public String pedidoNuevo(@PathVariable ("id")Integer id, Model modelo){
    	 log.info("ID MENU enviado como parametro {}", id);
    	 
    	 this.listaTipo =this.tipoServicio.listarTipoPedido();
    	 Menu menu= this.menuServicio.obtenerId(id);
    	 modelo.addAttribute("menu", menu);
	 
     return "pedidosventas/verMenuPedido";
     }
     
     @PostMapping("/detalle")
     public String agregarDetalle(@RequestParam Integer id, @RequestParam(name="cantidad") Integer cantidad, Model modelo) {
    	 DetallePedido detalle = new DetallePedido();
    	 Menu menu = new Menu();
    	 double sumaTotal = 0; 
    	 
    	 Menu menuOptional = this.menuServicio.obtenerId(id);
    	 log.info("Menu añadido: {}", menuOptional);
    	 log.info("Cantidad : {}", cantidad);
    	 
    	 menu = menuOptional;
    	 detalle.setCantidad(cantidad);
    	 detalle.setSubtotal(menu.getPrecio()*cantidad);
    	 detalle.setMenu(menu);
    	 
    	 //validar que no se añada 2 veces el producto
    	 
    	 Integer idMenu = menu.getId();	 
    	 boolean insertado = listaDetalles.stream().anyMatch(m->m.getMenu().getId() == idMenu);
    			
    	 if(!insertado) {
    		 listaDetalles.add(detalle);
    	 }
    	
    	 sumaTotal = listaDetalles.stream().mapToDouble(dt->dt.getSubtotal()).sum();
    	 
    	 venta.setTotal(sumaTotal);
    	 
    	 this.listaUsuarios = this.usuarioServicio.listarClientes(this.rolServivio.obtenerId(2));
    	 this.listaTipo =this.tipoServicio.listarTipoPedido();
    	 
    	 modelo.addAttribute("user", user);
    	 modelo.addAttribute("pedido", this.pedido);
    	 modelo.addAttribute("listaTipo", this.listaTipo);
    	 modelo.addAttribute("listaUsuarios", this.listaUsuarios);
    	 modelo.addAttribute("listaDetalles", this.listaDetalles);
    	 modelo.addAttribute("venta", venta);
    	 return "pedidosventas/detallePedido";
     }
     
     //Quitar detalle
     @GetMapping("/eliminar/detalle/{id}")
     public String quitarMenu(@PathVariable Integer id, Model modelo) {
    	 
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
    	 this.listaUsuarios = this.usuarioServicio.listarClientes(this.rolServivio.obtenerId(2));
    	 this.listaTipo =this.tipoServicio.listarTipoPedido();
    	 
    	 modelo.addAttribute("user", user);
    	 modelo.addAttribute("pedido", this.pedido);
    	 modelo.addAttribute("listaTipo", this.listaTipo);
    	 modelo.addAttribute("listaUsuarios", this.listaUsuarios);
    	 modelo.addAttribute("listaDetalles", this.listaDetalles);
    	 modelo.addAttribute("venta", venta);
    	 return "redirect:/cdsp/pedidos/verDetalle";
    	 
     }
     @GetMapping("/verDetalle")
     public String verDetalle(Model modelo) {
    	 this.listaUsuarios = this.usuarioServicio.listarClientes(this.rolServivio.obtenerId(2));
    	 this.listaTipo =this.tipoServicio.listarTipoPedido();
    	 
    	 
    	 modelo.addAttribute("user", user);
    	 modelo.addAttribute("pedido", this.pedido);
    	 modelo.addAttribute("listaTipo", this.listaTipo);
    	 modelo.addAttribute("listaUsuarios", this.listaUsuarios);
    	 modelo.addAttribute("listaDetalles", this.listaDetalles);
    	 modelo.addAttribute("venta", venta);
    	 return "/pedidosventas/detallePedido";
     }
     @GetMapping("/detalleuser/{id}")
     public String detallepedido(Model modelo, @PathVariable Long id) {
    	 this.listaUsuarios = this.usuarioServicio.listarClientes(this.rolServivio.obtenerId(2));
    	 this.listaTipo =this.tipoServicio.listarTipoPedido();
    	 user = this.usuarioServicio.obtenerId(id);
    	
    	 modelo.addAttribute("user", this.user);
    	 modelo.addAttribute("pedido", this.pedido);
    	 modelo.addAttribute("listaTipo", this.listaTipo);
    	 modelo.addAttribute("listaUsuarios", this.listaUsuarios);
    	 modelo.addAttribute("listaDetalles", this.listaDetalles);
    	 modelo.addAttribute("venta", venta);
    	 return "/pedidosventas/detallePedido";
     }
     
     @PostMapping("/guardar")
     public String guardarPedido(@ModelAttribute ("pedido") Pedido pedido, RedirectAttributes redirectAttrs) {
    	
    	
    	if (user == null || this.listaDetalles.isEmpty()) {
    		redirectAttrs.addFlashAttribute("clase", "success");
        	return "redirect:/cdsp/pedidos/verDetalle?error";	
    	}else {
    	
    	Date fechaCreacion = new Date();
    	pedido.setUsuario(user);
    	pedido.setFecha(fechaCreacion);
    	pedido.setEstado(this.estadoServicio.obtenerEstado(1));
    	
    	//Guardar pedido
    	this.pedidoServicio.agregar(pedido);
    	
    	//Guardar detalle
    	for(DetallePedido dt : listaDetalles) {
    		dt.setPedido(pedido);
    		detalleServicio.agregar(dt);
    	}
    	///limpiar lista detalles y pedidos
    	
    	pedido = new Pedido();
    	listaDetalles.clear();
    	venta= new Venta();
    	user= new Usuario();
    	return "redirect:/cdsp/pedidos";
    	}
		
    	
     }
     @GetMapping("/eliminar/pedido/{id}")
     public String eliminarPedido(@PathVariable("id") Long id, RedirectAttributes atributo){
    	try {
    		atributo.addFlashAttribute("exito");
    		 this.pedidoServicio.eliminar(id); 
    		}catch(Exception e) {
    			atributo.addFlashAttribute("error");
    		}
	    return "redirect:/cdsp/pedidos";
     }
  	@GetMapping("/venta/{id}")
  	public String cobrarVenta(@PathVariable ("id") Long id, Model modelo) {		
  		
  		double total = 0;
  		Date fechaCreacion = new Date();
  		Pedido pedidoVenta = this.pedidoServicio.obtenerId(id);
  		
    	List <DetallePedido> listaDetalles  = pedidoVenta.getDetalles();
    	pedidoVenta.setEstado(this.estadoServicio.obtenerEstado(9));
  		
  		total = listaDetalles.stream().mapToDouble(dt->dt.getSubtotal()).sum();
  		
   		venta.setFecha(fechaCreacion);
   		venta.setPedido(pedidoVenta);
   		venta.setTotal(total);
   		venta.setUsuario(pedidoVenta.getUsuario());
   		
   		this.ventaServicio.agregar(venta);
   		
   		emailService.sendMailCreateVenta(venta);

   		venta = new Venta ();
  		return "redirect:/cdsp/pedidos/ventas";
  	}
  	
  	@GetMapping("/promocion")
  	public String Promocion (Model modelo) {

  		String subject;
  		String body;
  		subject="ORGANO";
  		body="Hola!\n\nEste fin de semana, disfruta en nuetro restaurante platos con diferenter pormociones\n\n"
  				+ ""
  				+ "                            ¡Te esperamos!";

  		List <Usuario> usuariosPromo = this.usuarioServicio.listarClientes(this.rolServivio.obtenerId(2));
  		log.info("@@@ Lista usuarios: {}", usuariosPromo);
  		
  		emailService.sendMasiveMails(usuariosPromo, subject, body);
  		return "redirect:/cdsp/pedidos?promocion";
  	}
  	  
  	@GetMapping("/ventas")
  	public String ventasListar (Model modelo) {
  		this.listaVentas= this.ventaServicio.listarVentas();
  		modelo.addAttribute("listaVentas", this.listaVentas);
  		return "pedidosventas/ventas";
  	}
  	@GetMapping("/consultarventa/{id}")
  	public String consultarVenta(@PathVariable ("id") Long id, Model modelo) {
  		Venta venta = this.ventaServicio.obtenerId(id);
  		
    	List <DetallePedido> listaDetalles  = venta.getPedido().getDetalles();
    	
  		modelo.addAttribute("venta", venta);
  		modelo.addAttribute("listaDetalles", listaDetalles);
  	
  		return "pedidosventas/consultarDetalleVenta";
  	}
     @PostMapping("/buscar")
     public String buscarMenu(@RequestParam String nombre, Model modelo) {
    	 log.info("Nombre del producto {}", nombre);
    	 this.listaCateMenu=this.categoriaServicio.listarCateMenu();
    	 
    	 List <Menu> menus = this.menuServicio.listarMenu().stream().filter(m -> m.getNombre().contains(nombre)).collect(Collectors.toList());
    	 
    	 modelo.addAttribute("listaCateMenu", this.listaCateMenu);
    	 modelo.addAttribute("listaMenu", menus);
    	 return "pedidosventas/menuPedido";
     }
     
     @GetMapping("/buscar/categoria/{id}")
     public String buscarMenu(@PathVariable ("id") Integer id, Model modelo, HttpSession sesion) {
    	 log.info("id categoria {}", id);
    	 
    	 CateMenu categoria = this.categoriaServicio.obtenerId(id);
    	 List <Menu> menusPorCate = this.menuServicio.listarMenuPorCategoria(categoria);
    	 Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
    	 
    	 log.info("Lista {}", menusPorCate);
    	 modelo.addAttribute("listaCateMenu", this.listaCateMenu);
    	 modelo.addAttribute("listaMenuPorCate", menusPorCate);
    	 modelo.addAttribute("categoria", categoria); 
    	 modelo.addAttribute("usuario", usuario);
    	 return "pedidosventas/menuPedido";
     }
     @GetMapping("/editar/pedido/{id}")
     public String detallePedido(@PathVariable ("id")Long id, Model modelo) {
    	 log.info("ID PEDIDOS enviado como parametro {}", id);
    	 
    	 double sumaTotal = 0; 
    	 Pedido pedidoEditar = this.pedidoServicio.obtenerId(id);
    	 this.listaDetallesEditar = pedidoEditar.getDetalles(); 
    	 
    	 sumaTotal = this.listaDetallesEditar.stream().mapToDouble(dt->dt.getSubtotal()).sum(); 
    	 venta.setTotal(sumaTotal);
  	
    	 modelo.addAttribute("listaDetalles", this.listaDetallesEditar);
    	 modelo.addAttribute("venta", venta);
    	 modelo.addAttribute("pedido", pedidoEditar);
    	 
    	 int confirmarEstado; //variable para señaliza que tipo de acción poner en vista (Actualizar estado o boton de genrar venta)
    	 int confirmarVenta;
    	 if(this.ventaServicio.ventaConPedido(pedidoEditar) == true && pedidoEditar.getEstado().getId() == 9) {
    		 
    		 log.info("@@ Venta {}", this.ventaServicio.ventaConPedido(pedidoEditar));
    		 log.info("@@ estado {}", pedidoEditar.getEstado().getId());
    		 confirmarEstado = 1;
    		 confirmarVenta = 1;
    		 modelo.addAttribute("confirmacionEstado", confirmarEstado);
    		 modelo.addAttribute("confirmacionVenta", confirmarVenta);
    		 
    	 } else if (this.ventaServicio.ventaConPedido(pedidoEditar) == false && pedidoEditar.getEstado().getId() == 6) {
    		
    		 log.info("@@@@ Venta No hay");
    		 log.info("@@@@ estado {}", pedidoEditar.getEstado().getId());
    		 
    		 confirmarEstado = 1;
    		 modelo.addAttribute("confirmacionEstado", confirmarEstado);
    		 
    	 }else {
    		 log.info("@@@@ Venta No hay ni pedido");
    	 }
    	 return "pedidosventas/editarPedido"; 
    	 
     }
     @GetMapping("/estado/actualizar/{id}")
     public String actualizarEstado(@PathVariable("id") Long id, Model modelo) {
    	 
    	 Pedido pedidoEstado = new Pedido();
    	 pedidoEstado = this.pedidoServicio.obtenerId(id);
    	 int estado = pedidoEstado.getEstado().getId();
    	 if	(estado == 1) {
    	 pedidoEstado.setEstado(this.estadoServicio.obtenerEstado(2));
    	 
    	 }else if(estado == 2) {
    	 pedidoEstado.setEstado(this.estadoServicio.obtenerEstado(3)); 
    	 }else{
    		 pedidoEstado.setEstado(this.estadoServicio.obtenerEstado(6)); 
    	 }
    	 this.pedidoServicio.actualizar(pedidoEstado);
    	 
    	 return "redirect:/cdsp/pedidos/editar/pedido/{id} (id = ${pedidoEstado.id})}";
     }
     @GetMapping("/estado/cancelar/{id}")
     public String cancelarPedido(@PathVariable ("id")Long id, Model modelo) {
    	 
    	 Pedido pedidoCancel = this.pedidoServicio.obtenerId(id);

    	 pedidoCancel.setEstado(this.estadoServicio.obtenerEstado(11));
    	 
    	 
    	 this.pedidoServicio.actualizar(pedidoCancel);
    	 
    	 return "redirect:/cdsp/pedidos";
     }
     
     @GetMapping("/buscar/estado/{id}")
     public String buscarPedidoEstado(@PathVariable ("id") Integer id, Model modelo, HttpSession sesion) {
    	 log.info("id categoria {}", id);
    	 
    	 Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
    	 Estado estado = this.estadoServicio.obtenerEstado(id);
    	 List <Pedido> pedidosEstado = this.pedidoServicio.listarPedidoPorEstado(estado);
    	 this.listaEstado = this.estadoServicio.listarEstadosPedidos();
    	 
    	 modelo.addAttribute("listaEstado", this.listaEstado); 
    	 modelo.addAttribute("estado", estado); 
    	 modelo.addAttribute("listaPedidosEstado", pedidosEstado);
    	 modelo.addAttribute("usuario", usuario);
    	 return "pedidosventas/pedidos";
     }
     
     @GetMapping("/estadisticas")
     public String estadisticas( Model modelo, RedirectAttributes redirectAttrs) {
    	 
    	 int cantidadVentas = this.ventaServicio.listarVentas().size();
    	 double total = this.ventaServicio.listarVentas().stream().mapToDouble(v -> v.getTotal()).sum();
    	 Date fecha = new Date();
    	 List<Venta> ventas = this.ventaServicio.ultimasVentas(5);
    	 Double acumulado = this.ventaServicio.acumuladoVentasMes(fecha);
    	 List<VistaVentaMenuMes> ventaMenuMes = this.ventaMenuServicio.obtenerMenuMasVendido();
    	 
    	 if (acumulado == null) {
    		acumulado = (double) 0; 
    	 }
    	 
    	 
    	 int  msg;
    	 String estadistica;
    	 if(acumulado < 1000000) {
    		msg = 1;
    		estadistica = "Regular";
    	 }else {
    		msg = 2; 
    		estadistica = "Bien";
    	 }
    	 
    	 modelo.addAttribute("estadistica", estadistica);
    	 modelo.addAttribute("msg", msg);
    	 modelo.addAttribute("ventas", ventas);
    	 modelo.addAttribute("ventaMenu", ventaMenuMes);
    	 modelo.addAttribute("total", total);
    	 modelo.addAttribute("acumulado", acumulado);
    	 modelo.addAttribute("cantidadVentas", cantidadVentas);
     
     return "pedidosventas/estadisticas";
     }
     
     @GetMapping("/exportarPDF")
     public void exportarVentasPdf(HttpServletResponse response) throws DocumentException, IOException {
    	 response.setContentType("application/pdf");
    	 
    	 DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    	 String fechaActual = dateFormatter.format(new Date());
    	 
    	 String cabecera = "Content-Disposition";
    	 String valor = "attachment; filename=Ventas_" + fechaActual + ".pdf";
    	 
    	 response.setHeader(cabecera, valor);
    	 
    	 List<Venta> ventas = this.ventaServicio.listarVentas();
    	 
    	 VentaExporterPDF exporter = new VentaExporterPDF(ventas);
    	 exporter.exportar(response);
    	 
     }
     
     @GetMapping("/exportarEXCEL")
     public void exportarVentasEXCEL(HttpServletResponse response) throws DocumentException, IOException {
    	 response.setContentType("application/octet-stream");
    	 
    	 DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    	 String fechaActual = dateFormatter.format(new Date());
    	 
    	 String cabecera = "Content-Disposition";
    	 String valor = "attachment; filename=Ventas_" + fechaActual + ".xlsx";
    	 
    	 response.setHeader(cabecera, valor);
    	 
    	 List<Venta> ventas = this.ventaServicio.listarVentas();
    	 
    	 VentaExporterEXCEL exporter = new VentaExporterEXCEL(ventas);
    	 exporter.exportar(response);
    	 
     }
     
     @PostMapping("/buscarventas")
     public String buscarVentasPorFecha(@RequestParam(value="fechai", defaultValue = "2023-01-01") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechai,
    		 							@RequestParam(value="fechaf", defaultValue = "#{new java.util.Date()}") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaf, Model modelo) {
    	
    	 List<Venta> ventasFecha = this.ventaServicio.ventasPorFecha(fechai, fechaf);
    
    	modelo.addAttribute("ventasFecha",ventasFecha);
    	
    	
    	 return "pedidosventas/ventas";
     }
     
}