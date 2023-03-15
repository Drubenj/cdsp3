package com.mysena.cdsp3.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.*;
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
import org.springframework.web.multipart.MultipartFile;

import com.mysena.cdsp3.app.entities.CateMenu;
import com.mysena.cdsp3.app.entities.Menu;
import com.mysena.cdsp3.app.servicio.UploadFileService;
import com.mysena.cdsp3.app.servicioimp.CateMenuServicioimp;
import com.mysena.cdsp3.app.servicioimp.MenuServicioimp;


@Controller
@RequestMapping(path="/cdsp/menu")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST })
public class ProduccionContolador {
	
	private final Logger logger = LoggerFactory.getLogger(ProduccionContolador.class);

	@Autowired 
	private UploadFileService uploadFileService;
	
	
	@Autowired 
	private CateMenuServicioimp servicioimp;
	private List<CateMenu> listaCateMenu;
	
	@Autowired 
	private MenuServicioimp menuServicioimp;
	private List<Menu> listaMenu;
	
	@GetMapping("/categorias")
	 public String listarcategorias(Model modelo){
	     this.listaCateMenu= this.servicioimp.listarCateMenu();
	     
	     return "produccion/cateMenu";
	}
	@GetMapping("/")
	 public String listarMenu(Model modelo){
		this.listaMenu= this.menuServicioimp.listarMenu();
		modelo.addAttribute("listaMenu", this.listaMenu);
	    return "produccion/menu";
	}
	@GetMapping("/nuevo")
	 public String nuevoMenu(Model modelo){
	     this.listaCateMenu= this.servicioimp.listarCateMenu();
	     Menu menu = new Menu();
	     modelo.addAttribute("listaCateMenu", this.listaCateMenu);
	     modelo.addAttribute("menu", menu);
	     return "produccion/agregarMenu";
	}
	@PostMapping("/guardar" )
	 public String guardarMenu(@ModelAttribute ("menu") Menu menu, @RequestParam(name="img") MultipartFile file, HttpSession session) throws IOException{
	    logger.info("Objeto menú {}", menu); 
		
		//Guardar imagen
		
	    if (menu.getId()==null) { // cuando se crea un producto
			String nombreImagen= uploadFileService.saveImage(file);
			menu.setImagen(nombreImagen);
		}else {
			Menu m= new Menu();
			m=this.menuServicioimp.obtenerId(menu.getId());
			
			if (file.isEmpty()) { // editamos el producto pero no cambiamos la imagem
				
				menu.setImagen(m.getImagen());
			}else {// cuando se edita tbn la imagen			
				//eliminar cuando no sea la imagen por defecto
				if (!m.getImagen().equals("default.jpg")) {
					uploadFileService.deleteImage(m.getImagen());
				}
				String nombreImagen= uploadFileService.saveImage(file);
				menu.setImagen(nombreImagen);
			}
		}
		this.menuServicioimp.agregar(menu);
	     return "redirect:/cdsp/menu/";
	}
	@GetMapping("/editar/{id}")
	public String editarMenu(@PathVariable ("id") Integer id, Model modelo) {
		this.listaCateMenu= this.servicioimp.listarCateMenu();
		Menu menuEditar = this.menuServicioimp.obtenerId(id);
		
		modelo.addAttribute("listaCateMenu", this.listaCateMenu);
		modelo.addAttribute("menu", menuEditar);
		return "produccion/agregarMenu";
		
	}
	@GetMapping("/eliminar/{id}")
	 public String eliminarMenu(@PathVariable("id") Integer id){
		Menu menu = new Menu();
		menu = this.menuServicioimp.obtenerId(id);
		
		//Eliminar imagen si no es por defecto
		if(!menu.getImagen().equals("default.jpg")) {
			this.uploadFileService.deleteImage(menu.getImagen());
		}
		
	     this.menuServicioimp.eliminar(id);
	     return "redirect:/cdsp/menu/";
	}
	@GetMapping()
	public String buscarMenuPorCategoria(@PathVariable("id") Integer id) {
		this.servicioimp.obtenerId(id);
		return "";
	}

}




