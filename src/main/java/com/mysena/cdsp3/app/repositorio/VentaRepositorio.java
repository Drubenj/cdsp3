package com.mysena.cdsp3.app.repositorio;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mysena.cdsp3.app.entities.Pedido;
import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.entities.Venta;


@Repository
public interface VentaRepositorio extends JpaRepository<Venta, Long>{
	
	List<Venta> findByUsuario(Usuario usuario);
	
	Venta findByPedido(Pedido pedido);
	
	@Query(value = "SELECT * FROM Ventas  ORDER BY fecha desc limit ?1", nativeQuery = true)
	List<Venta> ultimasVentasPorFecha(int limit);
	
	@Query(value = "CALL sp_acumuladoVentasMes(:_fecha);", nativeQuery = true)
	Double ventaPorMes(@Param("_fecha") Date _fecha);
	
	List<Venta> findByFechaBetween(Date fechaInicio, Date fechaFin);

}
