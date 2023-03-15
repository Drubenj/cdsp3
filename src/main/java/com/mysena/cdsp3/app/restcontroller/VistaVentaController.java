package com.mysena.cdsp3.app.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysena.cdsp3.app.entities.VistaVentasMensuales;
import com.mysena.cdsp3.app.servicioimp.VistaVentasServicioimp;

@RestController
@EnableAutoConfiguration
public class VistaVentaController {
	
	@Autowired
	private VistaVentasServicioimp ventaServicio;
	

	 
	 @GetMapping("/ventas")
		//Returning is List is supported with JSON response only
		//If you want XML, then add a wrapper class as Root XML element, for example EmployeeList
		public List<VistaVentasMensuales> listaVentas() {
			return this.ventaServicio.listarVentasMen();
		}

}
