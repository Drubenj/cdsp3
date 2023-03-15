// Java Program to Create Rest Controller that
// Defines various API for Sending Mail

package com.mysena.cdsp3.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Importing required classes

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mysena.cdsp3.app.servicioimp.EmailServiceImpl;
import com.mysena.cdsp3.app.utilidades.EmailDetails;

// Annotation
@Controller
public class EmailController {
	
	private final Logger logger = LoggerFactory.getLogger(ProduccionContolador.class);
	
	@Autowired 
	private EmailServiceImpl emailService;

	@GetMapping("/cdsp/email")
	public String formularioEnviar (Model modelo) {
		EmailDetails email = new EmailDetails();
		modelo.addAttribute("email", email);
		return"administrador/enviarEmail";
	} 
	// Sending a simple Email
	@PostMapping("/sendMail")
	public String
	sendMail(@ModelAttribute ("email") EmailDetails email)
	{
		logger.info("Objeto emailService {}", email); 
		
		emailService.sendSimpleMail(email);
		return "redirect:/cdsp/email";
	}

	// Sending email with attachment
	@PostMapping("/sendMailWithAttachment")
	public String sendMailWithAttachment(
		@RequestBody EmailDetails detalles)
	{
		String status
			= emailService.sendMailWithAttachment(detalles);

		return status;
	}
}
