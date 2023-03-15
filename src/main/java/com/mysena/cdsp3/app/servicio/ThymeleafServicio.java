package com.mysena.cdsp3.app.servicio;

import java.util.Map;

public interface ThymeleafServicio {

	String createContent(String template, Map<String, Object> variables);
}
