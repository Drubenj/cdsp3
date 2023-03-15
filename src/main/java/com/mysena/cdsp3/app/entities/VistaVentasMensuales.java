package com.mysena.cdsp3.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="vista_ventasmensuales")

public class VistaVentasMensuales {

    @Id
    @Column(name="Mes")
    private String mes;
    
    
    @Column(name="Total")
    private Double total;


	public String getMes() {
		return mes;
	}


	public void setMes(String mes) {
		this.mes = mes;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}
    
    
}
