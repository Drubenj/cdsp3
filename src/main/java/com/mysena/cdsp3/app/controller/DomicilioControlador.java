package com.mysena.cdsp3.app.controller;

import java.util.List;

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

import com.mysena.cdsp3.app.entities.DetallePedido;
import com.mysena.cdsp3.app.entities.Domicilio;
import com.mysena.cdsp3.app.entities.Pedido;
import com.mysena.cdsp3.app.entities.Venta;
import com.mysena.cdsp3.app.servicio.DomicilioServicio;
import com.mysena.cdsp3.app.servicio.EstadoServicio;
import com.mysena.cdsp3.app.servicio.VentaServicio;
import com.mysena.cdsp3.app.servicioimp.PedidoServicioimp;

@Controller
@RequestMapping(path="/cdsp")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST })
public class DomicilioControlador {
	
@Autowired
private DomicilioServicio servicio;

@Autowired
private VentaServicio ventaServicio;
Venta venta = new Venta();

@Autowired
private PedidoServicioimp pedidoServicio;
private List<DetallePedido> listaDetalles;

@Autowired
private EstadoServicio estadoServicio;

@GetMapping("/domicilios")
public String listarDomicilio(Model modelo) {
	modelo.addAttribute("domicilios",servicio.listarTodosLosDomicilios());
	return "domicilio";
}
@GetMapping("/domiciliario/domicilios")
public String listarDomiciliodomiciliario(Model modelo) {
	modelo.addAttribute("domicilios",servicio.listarTodosLosDomicilios());
	return "domiciliario/domicilio";
}

@GetMapping("/cliente/domicilios")
public String listarDomicilioCliente(Model modelo) {
	modelo.addAttribute("domicilios",servicio.listarTodosLosDomicilios());
	return "cliente/domicilio";

}

@GetMapping("/domicilios/consultar/{id}")
public String consultarDomicilio(@PathVariable Long id, Model modelo) {
	
	Domicilio domicilio = this.servicio.obtenerDomicilioPorId(id);
	
	double sumaTotal = 0; 
	 Pedido pedido = domicilio.getPedido();
	 this.listaDetalles = pedido.getDetalles(); 
	 
	 sumaTotal = this.listaDetalles.stream().mapToDouble(dt->dt.getSubtotal()).sum(); 
	 venta.setTotal(sumaTotal);
	
	 modelo.addAttribute("listaDetalles", this.listaDetalles);
	 modelo.addAttribute("venta", venta);
	 modelo.addAttribute("pedido", pedido);
	 
	 int confirmarEstadoDom; //variable para señaliza que tipo de acción poner en vista (Actualizar estado o boton de genrar venta)
	 int confirmarVenta;
	 int estadoPedido; 
	 
	 if(pedido.getEstado().getId() == 9 && this.ventaServicio.ventaConPedido(pedido) == true && domicilio.getEstado().getId() == 6) {
		 
		 estadoPedido = 1;
		 confirmarEstadoDom = 1;
		 confirmarVenta = 1;
		 modelo.addAttribute("confirmacionEstado", confirmarEstadoDom);
		 modelo.addAttribute("confirmacionVenta", confirmarVenta);
		 modelo.addAttribute("confirmacionEstadoP", estadoPedido);
		 
	 } else if (pedido.getEstado().getId() == 6 && this.ventaServicio.ventaConPedido(pedido) == false && domicilio.getEstado().getId() == 6) {
		 
		 estadoPedido = 1;
		 confirmarEstadoDom = 1;
		 modelo.addAttribute("confirmacionEstado", confirmarEstadoDom);
		 modelo.addAttribute("confirmacionEstadoP", estadoPedido);
	 }
		 else if(pedido.getEstado().getId() == 6 && this.ventaServicio.ventaConPedido(pedido) == false && domicilio.getEstado().getId() != 6) {
			 
		 estadoPedido = 1;	
		 modelo.addAttribute("confirmacionEstadoP", estadoPedido);
		 
	 } 
	 
	modelo.addAttribute("domicilio", domicilio);
	return "/domicilios/consultarDomicilio";
	
}

@GetMapping("/domicilioscliente/consultar/{id}")
public String consultarDomiciliocliente(@PathVariable Long id, Model modelo) {
	
	Domicilio domicilio = this.servicio.obtenerDomicilioPorId(id);
	
	double sumaTotal = 0; 
	 Pedido pedido = domicilio.getPedido();
	 this.listaDetalles = pedido.getDetalles(); 
	 
	 sumaTotal = this.listaDetalles.stream().mapToDouble(dt->dt.getSubtotal()).sum(); 
	 venta.setTotal(sumaTotal);
	
	 modelo.addAttribute("listaDetalles", this.listaDetalles);
	 modelo.addAttribute("venta", venta);
	 modelo.addAttribute("pedido", pedido);
	 
	 int confirmarEstadoDom; //variable para señaliza que tipo de acción poner en vista (Actualizar estado o boton de genrar venta)
	 int confirmarVenta;
	 int estadoPedido; 
	 
	 if(pedido.getEstado().getId() == 9 && this.ventaServicio.ventaConPedido(pedido) == true && domicilio.getEstado().getId() == 6) {
		 
		 estadoPedido = 1;
		 confirmarEstadoDom = 1;
		 confirmarVenta = 1;
		 modelo.addAttribute("confirmacionEstado", confirmarEstadoDom);
		 modelo.addAttribute("confirmacionVenta", confirmarVenta);
		 modelo.addAttribute("confirmacionEstadoP", estadoPedido);
		 
	 } else if (pedido.getEstado().getId() == 6 && this.ventaServicio.ventaConPedido(pedido) == false && domicilio.getEstado().getId() == 6) {
		 
		 estadoPedido = 1;
		 confirmarEstadoDom = 1;
		 modelo.addAttribute("confirmacionEstado", confirmarEstadoDom);
		 modelo.addAttribute("confirmacionEstadoP", estadoPedido);
	 }
		 else if(pedido.getEstado().getId() == 6 && this.ventaServicio.ventaConPedido(pedido) == false && domicilio.getEstado().getId() != 6) {
			 
		 estadoPedido = 1;	
		 modelo.addAttribute("confirmacionEstadoP", estadoPedido);
		 
	 } 
	 
	modelo.addAttribute("domicilio", domicilio);
	return "/cliente/consultarDomicilio";
	
}

@GetMapping("/domiciliosdomiciliario/consultar/{id}")
public String consultarDomiciliodomiciliario(@PathVariable Long id, Model modelo) {
	
	Domicilio domicilio = this.servicio.obtenerDomicilioPorId(id);
	
	double sumaTotal = 0; 
	 Pedido pedido = domicilio.getPedido();
	 this.listaDetalles = pedido.getDetalles(); 
	 
	 sumaTotal = this.listaDetalles.stream().mapToDouble(dt->dt.getSubtotal()).sum(); 
	 venta.setTotal(sumaTotal);
	
	 modelo.addAttribute("listaDetalles", this.listaDetalles);
	 modelo.addAttribute("venta", venta);
	 modelo.addAttribute("pedido", pedido);
	 
	 int confirmarEstadoDom; //variable para señaliza que tipo de acción poner en vista (Actualizar estado o boton de genrar venta)
	 int confirmarVenta;
	 int estadoPedido; 
	 
	 if(pedido.getEstado().getId() == 9 && this.ventaServicio.ventaConPedido(pedido) == true && domicilio.getEstado().getId() == 6) {
		 
		 estadoPedido = 1;
		 confirmarEstadoDom = 1;
		 confirmarVenta = 1;
		 modelo.addAttribute("confirmacionEstado", confirmarEstadoDom);
		 modelo.addAttribute("confirmacionVenta", confirmarVenta);
		 modelo.addAttribute("confirmacionEstadoP", estadoPedido);
		 
	 } else if (pedido.getEstado().getId() == 6 && this.ventaServicio.ventaConPedido(pedido) == false && domicilio.getEstado().getId() == 6) {
		 
		 estadoPedido = 1;
		 confirmarEstadoDom = 1;
		 modelo.addAttribute("confirmacionEstado", confirmarEstadoDom);
		 modelo.addAttribute("confirmacionEstadoP", estadoPedido);
	 }
		 else if(pedido.getEstado().getId() == 6 && this.ventaServicio.ventaConPedido(pedido) == false && domicilio.getEstado().getId() != 6) {
			 
		 estadoPedido = 1;	
		 modelo.addAttribute("confirmacionEstadoP", estadoPedido);
		 
	 } 
	 
	modelo.addAttribute("domicilio", domicilio);
	return "/domiciliario/consultarDomicilio";
	
}

@GetMapping("/domicilios/estado/actualizar/{id}")
public String actualizarEstado(@PathVariable("id") Long id, Model modelo) {
	 
	Domicilio domicilio = this.servicio.obtenerDomicilioPorId(id);
	
		 int estado = this.servicio.obtenerDomicilioPorId(id).getEstado().getId();
	 
	 if	(estado == 1) {
	 domicilio.setEstado(this.estadoServicio.obtenerEstado(5));
	 
	 }else {
	 domicilio.setEstado(this.estadoServicio.obtenerEstado(6)); 
	 }
	 
	 this.servicio.actualizarDomicilio(domicilio);
	 
	 return "redirect:/cdsp/domicilios/consultar/{id} (id = ${domicilio.id})}";
}


@GetMapping("/domicilio/nuevo")
public String mostrarFormularioDomicilio(Model modelo) {
	Domicilio domicili = new Domicilio();
	modelo.addAttribute("domicili", domicili);
	return "registrardomicilio";
}
@PostMapping("/domicilio")
public String guardarDomicilio(@ModelAttribute("domicilio" ) Domicilio domicilio) {
	servicio.guardarDomicilio(domicilio);
	return "redirect:/domicilio";
}
@GetMapping("/domicilio/editar/{id}")
public String mostrarFomularioEditar(@PathVariable long id,Model modelo) {
	modelo.addAttribute("domicilio",servicio.obtenerDomicilioPorId(id));
	return "editardomicilio";
}
@PostMapping("/domicilio/{id}")
public String actualizarDomicilio(@PathVariable long id,@ModelAttribute("domicilio") Domicilio domicilio,Model modelo) {
Domicilio domicilioExistente= servicio.obtenerDomicilioPorId(id);	
domicilioExistente.setId(id);
domicilioExistente.setCiudad(domicilio.getCiudad());
domicilioExistente.setDireccion(domicilio.getDireccion());
domicilioExistente.setBarrio(domicilio.getBarrio());
domicilioExistente.setInmueble(domicilio.getInmueble());
domicilioExistente.setInterior(domicilio.getInterior());
servicio.actualizarDomicilio(domicilioExistente);
return "redirect:/domicilio";

}
@GetMapping("/domicilio/{id}")
public String eliminarDomicilio(@PathVariable long id){
	servicio.eliminarDomicilio(id);
	return "redirect:/domicilio";
}
@GetMapping("/BARRADOMICILIARIO.html")
public String barradomiciliario() {
return "BARRADOMICILIARIO";
}
@GetMapping("/encabezadousu.html")
public String encabezadousu() {
return "encabezadousu";
}
@GetMapping("/BARRACR.html")
public String barra2cr() {
return "BARRACR";
}
@GetMapping("/foteroregano.html")
public String foteroregano() {
return "foteroregano";
}
@GetMapping("/domicilios.html")
public String domicilios() {
return "domicilios";
}
@GetMapping("/domiciliosDOMI.html")
public String domiciliosDOMI() {
return "domiciliosDOMI";
}
@GetMapping("/InterfazClienteDomicilios.html")
public String InterfazClienteDomicilios() {
return "InterfazClienteDomicilios";
}
@GetMapping("/InterfazClienteDomiciliosCR.html")
public String InterfazClienteDomiciliosCR() {
return "InterfazClienteDomiciliosCR";
}
@GetMapping("/Interfaz3Conjava.html")
public String Interfaz3Conjava() {
return "Interfaz3Conjava";
}
@GetMapping("/Interfaz2Conjava.html")
public String Interfaz2Conjava() {
return "Interfaz2Conjava";
}
@GetMapping("/GraficosDomicilios.html")
public String GraficosDomicilios() {
return "GraficosDomicilios";
}
@GetMapping("/cliente/GraficosDomicilios.html")
public String ClienteGraficosDomicilios() {
return "GraficosDomicilios";
}
@GetMapping("/domiciliario/GraficosDomicilios.html")
public String domiciliarioGraficosDomicilios() {
return "GraficosDomicilios";
}
@GetMapping("/domiciliario/Domicilios.html")
public String DomiciliarioDomicilios() {
return "DomiciliarioDomicilios";
}
@GetMapping("/MIPERFIL.html")
public String MIPERFIL() {
return "MIPERFIL";
}
@GetMapping("/MIPERFILEMDOMI.html")
public String MIPERFILEMDOMI() {
return "MIPERFILEMDOMI";
}
@GetMapping("/nosotros.html")
public String nosotros() {
	return "nosotros";
}
@GetMapping("/PedidosMESERO.html")
public String PedidosMESERO() {
return "PedidosMESERO";
}
@GetMapping("/CONTACTENOS.html")
public String CONTACTENOS() {
return "CONTACTENOS";
}
@GetMapping("/CONTACTENOSCR.html")
public String CONTACTENOSCR() {
return "CONTACTENOSCR";
}

}




