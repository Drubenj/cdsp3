package com.mysena.cdsp3.app.servicioimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysena.cdsp3.app.entities.VistaVentaMenuMes;
import com.mysena.cdsp3.app.entities.VistaVentasMensuales;
import com.mysena.cdsp3.app.repositorio.VistaVentaMenuMesRepositorio;
import com.mysena.cdsp3.app.repositorio.VistaVentaRepositorio;
import com.mysena.cdsp3.app.servicio.VistaVentaServicio;

@Service
public class VistaVentasServicioimp implements VistaVentaServicio{

	@Autowired
	private VistaVentaRepositorio repositorioVentas;
	
	@Autowired
	private VistaVentaMenuMesRepositorio repositorioMenuVenta;
	
	@Override
	public List<VistaVentasMensuales> listarVentasMen() {
		return this.repositorioVentas.findAll();
	}

	@Override
	public List<VistaVentaMenuMes> obtenerMenuMasVendido() {
		// TODO Auto-generated method stub
		return this.repositorioMenuVenta.findAll();
	}

}
