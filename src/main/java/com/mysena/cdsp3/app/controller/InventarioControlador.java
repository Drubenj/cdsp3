package com.mysena.cdsp3.app.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.mysena.cdsp3.app.entities.Insumo;
import com.mysena.cdsp3.app.entities.Inventario;
import com.mysena.cdsp3.app.entities.Movimiento;
import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.servicio.InsumoService;
import com.mysena.cdsp3.app.servicio.InventarioService;
import com.mysena.cdsp3.app.servicio.MovimientoService;
import com.mysena.cdsp3.app.servicio.UsuarioServicio;



@Controller
@RequestMapping(path="/cdsp/inventarioadmin")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST })
public class InventarioControlador {

	@Autowired
	private MovimientoService movimientoService;
	
	@Autowired
	private UsuarioServicio usuariosService;
	
	@Autowired
	private InsumoService insumoService;
	
	@Autowired
	private InventarioService inventarioService;
	
	
	@GetMapping({""})
	private String listarInventario(Model modelo) {
		modelo.addAttribute("inventario", inventarioService.listarTodosLosInventarios());
		return "inventario/inventarioadmin";
	}
	@GetMapping("/nuevo")
	public String mostrarFormularioRegistroInventario(Model modelo) {
		Inventario inventari = new Inventario();
		List <Insumo> insumos = this.insumoService.listarTodosLosInsumos();
		List <Movimiento> movimientos = this.movimientoService.listaMovimiento();
		modelo.addAttribute("insumos", insumos);
		modelo.addAttribute("inventari", inventari);
		modelo.addAttribute("movimientos", movimientos);
		return "inventario/registrarinventario";
	}
	@PostMapping("/registrar")
	public String guardarInventario(@ModelAttribute("inventario") Inventario inventario) {
		inventario.setCantidadtotal(inventario.getCantidadmovimiento());
		inventarioService.guardarInventario(inventario);
		return "redirect:/cdsp/inventarioadmin/";
	}
	@GetMapping("/editar/{idinventario}")
	public String mostrarFormularioDeEditar(@PathVariable Integer idinventario, Model modelo) {
		modelo.addAttribute("inventario", inventarioService.obtenerInventarioPorId(idinventario));
		return "/editarinventarioadmin";
	}
	@PostMapping("/{idinventario}")
	public String actualizarInventario(@PathVariable Integer idinventario, @ModelAttribute("inventario") Inventario inventario, Model modelo) {
		Inventario inventarioExistentes = inventarioService.obtenerInventarioPorId(idinventario);
		inventarioExistentes.setIdinventario(idinventario);
		inventarioExistentes.setFecha(inventario.getFecha());
		inventarioExistentes.setInsumo(inventario.getInsumo());
		inventarioExistentes.setMovimiento(inventario.getMovimiento());
		inventarioExistentes.setCantidadtotal(inventario.getCantidadtotal());
		inventarioService.actualizarInventario(inventarioExistentes);
		return "redirect:/cdsp/inventarioadmin";
	}
	@GetMapping("/eliminar/{idinventario}")
	public String eliminarInventario(@PathVariable Integer idinventario) {
		inventarioService.eliminarInventario(idinventario);
		return "redirect:/cdsp/inventarioadmin";
	}
	@GetMapping({"/inventario"})
	public String listarInventarios(Model modelo) {
		modelo.addAttribute("inventario", inventarioService.listarTodosLosInventarios());
		return "/inventario";
	}
	
	
}
