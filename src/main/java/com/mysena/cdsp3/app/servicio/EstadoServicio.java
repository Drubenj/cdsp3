package com.mysena.cdsp3.app.servicio;

import java.util.List;

import com.mysena.cdsp3.app.entities.Estado;

public interface EstadoServicio {
		public List<Estado> listarTodosLosEstados();
		public Estado guardarEstado(Estado estad);
		public Estado obtenerEstado(Integer id);
		public Estado actualizarEstado(Estado estado);
		public void eliminarEstado(Integer ID_Estado);
		public List<Estado> listarEstadosPedidos();
		public List<Estado> listarEstadosPedidosCocina();
}
