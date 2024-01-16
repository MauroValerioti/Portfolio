package com.course.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.course.ecommerce.model.Orden;
import com.course.ecommerce.model.Usuario;

public interface IOrdenService {

	List<Orden> findAll();

	Optional<Orden> findById(Integer id);
	
	Orden save(Orden orden);

	String generarNroOrden();
	
	List<Orden> findByUsuario(Usuario usuario);
	
}
