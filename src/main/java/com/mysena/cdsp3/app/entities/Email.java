package com.mysena.cdsp3.app.entities;

public class Email {

	private String remitente;
	private String body;
	private String Asunto;
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAsunto() {
		return Asunto;
	}
	public void setAsunto(String asunto) {
		Asunto = asunto;
	}
	public Email(String remitente, String body, String asunto) {
		super();
		this.remitente = remitente;
		this.body = body;
		Asunto = asunto;
	}
	public Email() {
		super();
	}
	@Override
	public String toString() {
		return "Email [remitente=" + remitente + ", body=" + body + ", Asunto=" + Asunto + "]";
	}
	
	
}
