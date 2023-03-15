// Java Program to Illustrate Creation Of
// Service implementation class

package com.mysena.cdsp3.app.servicioimp;

// Importing required classes
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.controller.PedidoControlador;
import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.entities.Venta;
import com.mysena.cdsp3.app.servicio.EmailService;
import com.mysena.cdsp3.app.servicio.ThymeleafServicio;
import com.mysena.cdsp3.app.utilidades.EmailDetails;

// Annotation
@Service
// Class
// Implementing EmailService interface
public class EmailServiceImpl implements EmailService {

	private final Logger log = LoggerFactory.getLogger(PedidoControlador.class);
	
	@Autowired 
	private JavaMailSender javaMailSender;
	
	@Autowired
	ThymeleafServicio thymeleafServicio;

	@Value("${spring.mail.username}")
	private String remitente;

	// Method 1
	// To send a simple email
	public String sendSimpleMail(EmailDetails detalles)
	{

		// Try block to check for exceptions
		try {

			// Creating a simple mail message
			SimpleMailMessage mailMessage= new SimpleMailMessage();

			// Setting up necessary details
			mailMessage.setFrom(remitente);
			mailMessage.setTo(detalles.getRecipient());
			mailMessage.setText(detalles.getMsgBody());
			mailMessage.setSubject(detalles.getSubject());
			mailMessage.setSentDate(null);

			// Sending the mail
			javaMailSender.send(mailMessage);
			return "Correo enviado exitosamente...";
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			return "Error mientras se enviaba el correo";
		}
	}

	// Method 2
	// To send an email with attachment
	public String
	sendMailWithAttachment(EmailDetails detalles)
	{
		// Creating a mime message
		MimeMessage mimeMessage
			= javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;

		try {

			// Setting multipart as true for attachments to
			// be send
			mimeMessageHelper
				= new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(remitente);
			mimeMessageHelper.setTo(detalles.getRecipient());
			mimeMessageHelper.setText(detalles.getMsgBody());
			mimeMessageHelper.setSubject(detalles.getSubject());

			// Adding the attachment
			FileSystemResource file
				= new FileSystemResource(
					new File(detalles.getAttachment()));

			mimeMessageHelper.addAttachment(
				file.getFilename(), file);

			// Sending the mail
			javaMailSender.send(mimeMessage);
			return "Correo enviado con exito";
		}

		// Catch block to handle MessagingException
		catch (MessagingException e) {

			// Display message when exception occurred
			return "Error mientras se enviaba el correo!!!";
		}
	}

	@Override
	public void sendMasiveMails(List<Usuario> listaUsuarios, String subject, String body) {
		
		try {
			
			log.info("@@@ Servicio  Lista usuarios: {}",listaUsuarios);
			for (Usuario usua: listaUsuarios) {
				
				log.info("@@@## Servicio Lista usuarios: {}",usua);
				SimpleMailMessage mensaje= new SimpleMailMessage();

				mensaje.setFrom(remitente);
				mensaje.setTo(usua.getCorreo());
				mensaje.setSubject(subject);
				mensaje.setText(body);
				
				javaMailSender.send(mensaje);
				}
			
			}

		// Catch block to handle the exceptions
		catch (Exception e) {
		}
	}

	@Override
	public void sendMailTest(Usuario usuario) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			
			
			MimeMessageHelper helper = new MimeMessageHelper(
					message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, 
					StandardCharsets.UTF_8.name());
			
			helper.setFrom(remitente);
			helper.setText(thymeleafServicio.createContent("pedidosventas/correoMensaje.html", null), true);
			helper.setCc("william.tijo@misena.edu.co");
			helper.setTo(usuario.getCorreo());
			helper.setSubject("MailTest con HTML");
			
			javaMailSender.send(message);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void sendMailCreateCliente(Usuario usuario) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(
					message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, 
					StandardCharsets.UTF_8.name()
			);
			
			helper.setTo(usuario.getCorreo());
			
			//Object[] bccObject = usuario.getMailCC().toArray();
			//String[] bcc = Arrays.copyOf(bccObject, bccObject.length, String[].class);
			//helper.setBcc(bcc);
			
			Map<String, Object> variables = new HashMap<>();
			variables.put("nombres", usuario.getNombres());
			variables.put("apellidos", usuario.getApellidos());
			variables.put("correo", usuario.getCorreo());
			helper.setSubject("Registro de usuario");
			
			helper.setText(thymeleafServicio.createContent("usuarios/notificacionCreacionUser.html", variables), true);
			helper.setFrom(remitente);
			
			javaMailSender.send(message);
			
			
		}catch(Exception  e) {
		e.printStackTrace();
		}
	}

	@Override
	public void sendMailCreateVenta(Venta venta) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(
					message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, 
					StandardCharsets.UTF_8.name()
			);
			
			helper.setTo(venta.getUsuario().getCorreo());
			
			//Object[] bccObject = usuario.getMailCC().toArray();
			//String[] bcc = Arrays.copyOf(bccObject, bccObject.length, String[].class);
			//helper.setBcc(bcc);
			
			Map<String, Object> variables = new HashMap<>();
			variables.put("nombres", venta.getUsuario().getNombres());
			variables.put("apellidos", venta.getUsuario().getApellidos());
			variables.put("id", venta.getId());
			variables.put("fecha", venta.getFecha());
			variables.put("total", venta.getTotal());
			helper.setSubject("Comprobante de venta");
			
			helper.setText(thymeleafServicio.createContent("pedidosventas/notificacionCreacionVenta.html", variables), true);
			helper.setFrom(remitente);
			
			javaMailSender.send(message);
			
			
		}catch(Exception  e) {
		e.printStackTrace();
		}
		
	}
}
