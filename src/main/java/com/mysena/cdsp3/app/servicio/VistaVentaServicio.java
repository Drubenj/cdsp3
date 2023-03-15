package com.mysena.cdsp3.app.servicio;

import java.util.List;

import com.mysena.cdsp3.app.entities.VistaVentaMenuMes;
import com.mysena.cdsp3.app.entities.VistaVentasMensuales;

public interface VistaVentaServicio {

	public List<VistaVentasMensuales> listarVentasMen();
	
	public List<VistaVentaMenuMes> obtenerMenuMasVendido();
}
