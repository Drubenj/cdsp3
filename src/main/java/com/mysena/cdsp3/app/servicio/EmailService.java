// Java Program to Illustrate Creation Of
// Service Interface

package com.mysena.cdsp3.app.servicio;

import java.util.List;

import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.entities.Venta;
import com.mysena.cdsp3.app.utilidades.EmailDetails;

// Importing required classes

// Interface
public interface EmailService {

	// Method
	// To send a simple email
	String sendSimpleMail(EmailDetails details);
	
	void sendMasiveMails(List <Usuario> listaUsuarios, String subject, String body);
	// Method
	// To send an email with attachment
	String sendMailWithAttachment(EmailDetails details);
	
	void sendMailTest(Usuario usuario);
	
	void sendMailCreateCliente(Usuario usuario);
	
	void sendMailCreateVenta(Venta venta);
}
