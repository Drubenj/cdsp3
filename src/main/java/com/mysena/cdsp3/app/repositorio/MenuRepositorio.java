package com.mysena.cdsp3.app.repositorio;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysena.cdsp3.app.entities.CateMenu;
import com.mysena.cdsp3.app.entities.Menu;


@Repository
public interface MenuRepositorio  extends JpaRepository<Menu, Integer>{
	
	List<Menu> findByCategoria(CateMenu categoria);
}
