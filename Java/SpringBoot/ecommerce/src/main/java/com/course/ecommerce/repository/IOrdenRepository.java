package com.course.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.ecommerce.model.Orden;
import com.course.ecommerce.model.Usuario;

import java.util.List;


@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer>{
	
	List<Orden> findByUsuario(Usuario usuario);
	
}
