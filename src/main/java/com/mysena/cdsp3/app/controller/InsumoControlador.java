package com.mysena.cdsp3.app.controller;


import java.util.ArrayList;
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

import com.mysena.cdsp3.app.entities.Cateins;
import com.mysena.cdsp3.app.entities.Estado;
import com.mysena.cdsp3.app.entities.Insumo;
import com.mysena.cdsp3.app.servicio.CateinsService;
import com.mysena.cdsp3.app.servicio.EstadoServicio;
import com.mysena.cdsp3.app.servicio.InsumoService;



@Controller
@RequestMapping(path="/cdsp/insumo")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST })
public class InsumoControlador {

	@Autowired
	private EstadoServicio estadoService;
	
	
	@Autowired
	private InsumoService insumoService;
	
	@Autowired
	private CateinsService categoriaServicio;
	
	
	@GetMapping({""})
	public String listarInsumos(Model modelo) {
		List <Insumo> insumos = insumoService.listarTodosLosInsumos();
		modelo.addAttribute("insumo", insumos );
		return "insumo";
	}
	@GetMapping("/nuevo")
	public String mostrarFormularioRegistroInsumo(Model modelo) {
		Insumo insum = new Insumo();
		Estado activo = this.estadoService.obtenerEstado(6);
		Estado inactivo = this.estadoService.obtenerEstado(7);
		
		List <Cateins> categorias = this.categoriaServicio.listaCategoria();
		List<Estado> estadoInsumo = new ArrayList();
		estadoInsumo.add(activo);
		estadoInsumo.add(inactivo);
		modelo.addAttribute("insum", insum);
		modelo.addAttribute("estados", estadoInsumo);
		modelo.addAttribute("categorias", categorias);
		return "inventario/registrarinsumo";
	}
	@PostMapping("/guardar")
	public String guardarInsumo(@ModelAttribute("insumo") Insumo insumo) {
		insumoService.guardarInsumos(insumo);
		return "redirect:/cdsp/insumo";
	}
	@GetMapping("/editar/{idinsumo}")
	public String mostrarFormularioDeEditar(@PathVariable Integer idinsumo, Model modelo) {
		modelo.addAttribute("insumo", insumoService.obtenerInsumoPorId(idinsumo));
		return "editarinsumo";
	}
	@PostMapping("/actualizar/{idinsumo}")
	public String actualizarInsumo(@PathVariable Integer idinsumo, @ModelAttribute("insumo") Insumo insumo, Model modelo) {
		Insumo insumoExistentes = insumoService.obtenerInsumoPorId(idinsumo);
		insumoExistentes.setIdinsumo(idinsumo);
		insumoExistentes.setNombre(insumo.getNombre());
		insumoExistentes.setPrecio(insumo.getPrecio());
		insumoExistentes.setEstado(insumo.getEstado());
		insumoExistentes.setCateins(insumo.getCateins());
		insumoService.actualizarInsumo(insumoExistentes);
		return "redirect:/cdsp/insumo";
	}
	@GetMapping("/eliminar/{idinsumo}")
	public String eliminarInsumo(@PathVariable Integer idinsumo) {
		insumoService.eliminarInsumo(idinsumo);
		return "redirect:/cdsp/insumo";
	}
	@GetMapping({"/insumojefe"})
	public String listarTodosInsumos(Model modelo) {
		modelo.addAttribute("insumo", insumoService.listarTodosLosInsumos());
		return "insumojefe";
	}

	@GetMapping("/Grafico.html")
	public String Grafico() {
	return "Grafico";
}
}
