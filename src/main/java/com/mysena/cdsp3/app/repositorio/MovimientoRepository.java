package com.mysena.cdsp3.app.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysena.cdsp3.app.entities.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

}
