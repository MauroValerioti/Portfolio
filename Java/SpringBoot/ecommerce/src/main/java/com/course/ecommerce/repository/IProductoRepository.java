package com.course.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.ecommerce.model.Producto;

@Repository //esto es para que spring lo interprete como un repository y lo inyecte donde lo querramos usar
public interface IProductoRepository extends JpaRepository<Producto, Integer>{
	
}
