package com.mysena.cdsp3.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControlador {

	@GetMapping("/foteroregano.html")
	public String foteroregano() {
	return "foteroregano";
	}
	@GetMapping("/encabezado.html")
	public String encabezado() {
	return "encabezado";
	}
	@GetMapping("/BARRACR.html")
	public String BARRACR() {
	return "BARRACR";
	}
	@GetMapping("/BARRAJEFE.html")
	public String BARRAJEFE() {
	return "BARRAJEFE";
	}
}
