package com.mysena.cdsp3.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ventas_menu_mes")
public class VistaVentaMenuMes {
	
	 @Id
	    @Column(name="nombre")
	    private String nombre;
	    
	    @Column(name="cantidad")
	    private int cantidad;
	    
	    @Column(name="acumulado")
	    private Double acumulado;
	    
	    @Column(name="mes")
	    private String mes;

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}

		public Double getAcumulado() {
			return acumulado;
		}

		public void setAcumulado(Double acumulado) {
			this.acumulado = acumulado;
		}

		public String getMes() {
			return mes;
		}

		public void setMes(String mes) {
			this.mes = mes;
		}
	    
	    
}
