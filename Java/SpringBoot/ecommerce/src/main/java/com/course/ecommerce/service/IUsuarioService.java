package com.course.ecommerce.service;

import java.util.Optional;

import com.course.ecommerce.model.Usuario;

public interface IUsuarioService { // esta es la interfaz que usara luego el usuario service

	Optional<Usuario> findById(Integer id);

	Usuario save(Usuario usuario);
	
	Optional<Usuario> findByEmail(String email);
}
