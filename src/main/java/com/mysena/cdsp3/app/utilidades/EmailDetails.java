package com.mysena.cdsp3.app.utilidades;


	

import java.util.Date;
import java.util.List;

import com.mysena.cdsp3.app.entities.Usuario;

// Importing required classes
	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.NoArgsConstructor;

	// Annotations
	@Data
	@AllArgsConstructor
	@NoArgsConstructor

	// Class
	public class EmailDetails {

		// Class data members
		private String recipient;
		private String msgBody;
		private String subject;
		private String attachment;
		private Date date;
		private List <Usuario> listaUsuarios;
		
		public String getRecipient() {
			return recipient;
		}
		public void setRecipient(String recipient) {
			this.recipient = recipient;
		}
		public String getMsgBody() {
			return msgBody;
		}
		public void setMsgBody(String msgBody) {
			this.msgBody = msgBody;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getAttachment() {
			return attachment;
		}
		public void setAttachment(String attachment) {
			this.attachment = attachment;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public List<Usuario> getListaUsuarios() {
			return listaUsuarios;
		}
		public void setListaUsuarios(List<Usuario> listaUsuarios) {
			this.listaUsuarios = listaUsuarios;
		}

		
	}


