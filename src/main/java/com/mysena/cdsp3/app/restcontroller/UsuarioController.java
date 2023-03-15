package com.mysena.cdsp3.app.restcontroller;

import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.servicioimp.UsuarioServicioimp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(path="/api/demousuario")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST })
public class UsuarioController {

    @Autowired
     private UsuarioServicioimp usuarioServicioimp;  
     private List<Usuario> listaUsuarios;
     
     @GetMapping("/listar")
     public ResponseEntity<Map<String,Object>> listarTodos(){
        Map<String,Object> response = new HashMap();  
         this.listaUsuarios=this.usuarioServicioimp.listarTodos();
         if (listaUsuarios.size()>0) {
            response.put("succes", "OK");
            response.put("data", this.listaUsuarios); 
         }else{
             response.put("succes", "OK");
             response.put("data", "No hay registros");
         }
         return new ResponseEntity<>(response,HttpStatus.OK);
     }
     
     @PostMapping("/crear")
     public ResponseEntity<Map<String,Object>> agregar(@RequestBody Map<String,Object> request){
      Map<String,Object> response = new HashMap();
      Usuario usuario = new Usuario();
      usuario.setId(Long.parseLong(request.get("id").toString()));
      usuario.setIdUsuario(Long.parseLong(request.get("id_usuario").toString()));
      usuario.setNombres(request.get("nombres").toString());
      usuario.setApellidos(request.get("apellidos").toString());
      usuario.setTelefono(Long.parseLong(request.get("telefono").toString()));
      usuario.setDireccion(request.get("direccion").toString());
      usuario.setCorreo(request.get("correo").toString());
      this.usuarioServicioimp.agregar(usuario);
      response.put("succes", "OK");
      response.put("sms", "registro exitoso");
      return new ResponseEntity<>(response,HttpStatus.OK);
     }
     
     @PostMapping("/actualizar")
     public ResponseEntity<Map<String,Object>> actualizar(@RequestBody Map<String,Object> request){
         Map<String,Object> response = new HashMap();
         Usuario usuario = new Usuario();
         usuario.setIdUsuario(Long.parseLong(request.get("id_usuario").toString()));
         usuario.setNombres(request.get("nombres").toString());
         usuario.setApellidos(request.get("apellidos").toString());
         usuario.setTelefono(Long.parseLong(request.get("telefono").toString()));
         usuario.setDireccion(request.get("direccion").toString());
         usuario.setCorreo(request.get("correo").toString());
         this.usuarioServicioimp.actualizar(usuario);
         response.put("succes", "OK");
         response.put("sms", "registro exitoso");
         return new ResponseEntity<>(response,HttpStatus.OK);
     
     }
     
}
