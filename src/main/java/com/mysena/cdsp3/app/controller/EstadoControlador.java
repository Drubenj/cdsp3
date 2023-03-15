package com.mysena.cdsp3.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mysena.cdsp3.app.entities.Estado;
import com.mysena.cdsp3.app.servicioimp.EstadoServicioimp;


@Controller
public class EstadoControlador {

	@Autowired
	private EstadoServicioimp servicio;
	
	@GetMapping({"/estado"})
	public String listarEstados(Model modelo) {
		modelo.addAttribute("estado", servicio.listarTodosLosEstados());
		return "estado";
	}
	@GetMapping("/crear_estado/nuevo")
	public String mostrarFormularioRegistroEstado(Model modelo) {
		Estado estad = new Estado();
		modelo.addAttribute("estad", estad);
		return "crear_estado";
	}
	@PostMapping("/estado")
	public String guardarEstado(@ModelAttribute("estado") Estado estado) {
		servicio.guardarEstado(estado);
		return "redirect:/estado";
	}
	@GetMapping("/estado/editar/{ID_Estado}")
	public String mostrarFormularioDeEditar(@PathVariable Integer ID_Estado, Model modelo) {
		modelo.addAttribute("estado", servicio.obtenerEstado(ID_Estado));
		return "editar_estado";
	}
	@PostMapping("/estado/{ID_Estado}")
	public String actualizarEstado(@PathVariable Integer ID_Estado, @ModelAttribute("estado") Estado estado, Model modelo) {
		Estado estadoExistentes = servicio.obtenerEstado(ID_Estado);
		estadoExistentes.setId(ID_Estado);
		estadoExistentes.setNombre(estado.getNombre());
		servicio.actualizarEstado(estadoExistentes);
		return "redirect:/estado";
	}
	@GetMapping("/estado/{ID_Estado}")
	public String eliminarEstado(@PathVariable Integer ID_Estado) {
		servicio.eliminarEstado(ID_Estado);
		return "redirect:/estado";
	}
}
