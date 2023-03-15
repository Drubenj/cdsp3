/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mysena.cdsp3.app.controller;

import com.mysena.cdsp3.app.entities.Rol;
import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.servicioimp.EmailServiceImpl;
import com.mysena.cdsp3.app.servicioimp.RolServicioimp;
import com.mysena.cdsp3.app.servicioimp.UsuarioServicioimp;
import org.slf4j.*;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Willy
 */
@Controller
@RequestMapping(path="/cdsp/usuarios")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST })
public class UsuarioControlador {
	private final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);
	
	@Autowired
	private UsuarioServicioimp usuarioSerImp;
	private List<Usuario> listaUsuarios;

	@Autowired
	private RolServicioimp rolSerImp;
	private List<Rol> listaRoles;
	
	@Autowired
	private EmailServiceImpl mailService;

	@GetMapping("/vertodos")
	public String listarAdmin(Model modelo) {
		this.listaUsuarios = this.usuarioSerImp.listarTodos();
		modelo.addAttribute("listaUsuarios", this.listaUsuarios);
		return "usuarios/usuarios";
	}

	@GetMapping("/nuevo")
	public String nuevoUsuarioAdmin(Model modelo) {
		this.listaRoles = this.rolSerImp.listarRoles();
		Usuario usuario = new Usuario();
		modelo.addAttribute("listaRoles", this.listaRoles);
		modelo.addAttribute("usuario", usuario);
		return "usuarios/agregarUsuario";
	}

	@PostMapping("/guardar")
	public String guardarUsuarioAdmin( @ModelAttribute("usuario") Usuario usuario) {
		
		this.usuarioSerImp.agregar(usuario);
		mailService.sendMailCreateCliente(usuario);

		return "redirect:/cdsp/usuarios/vertodos";
	}
	
	public String enviarNotificacion() {
		return "redirect:/cdsp/usuarios/vertodos";
	}
	
	@GetMapping("/editar/{id}")
	public String editarUsuarioAdmin(@PathVariable("id") Long id, Model modelo) {
		this.listaRoles = this.rolSerImp.listarRoles();
		Usuario usuarioEditar = this.usuarioSerImp.obtenerId(id);

		modelo.addAttribute("listaRoles", this.listaRoles);
		modelo.addAttribute("usuario", usuarioEditar);
		return "usuarios/agregarUsuario";
	}
	

	@GetMapping("/eliminar/{id}")
	public String eliminarUsuarioAdmin(@PathVariable("id") Long id) {
		this.usuarioSerImp.eliminar(id);
		return "redirect:/cdsp/usuarios";
	}
	
	@PostMapping("/guardarcliente")
	public String guardarCliente(Usuario usuario,  RedirectAttributes redirectAttrs) {
		logger.info("Usuaurio registri {},", usuario);
		
		Rol rol = new Rol();
		rol = this.rolSerImp.obtenerId(2);
		
		List<Rol> roles = new ArrayList <Rol>();
		roles.add(rol);
		
		usuario.setListaRoles(roles);
		this.usuarioSerImp.agregar(usuario);
		
		redirectAttrs.addFlashAttribute("clase", "success");
		return "redirect:/cdsp/registro?exito";
	}

}
