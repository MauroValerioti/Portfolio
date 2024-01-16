package com.course.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.course.ecommerce.model.Producto;

/*Esta interfaz contiene la definicion de los metodos CRUD que luego seran definidos.
 * @author: Mauro Valerioti
 * @version: 8/12/2023
 */
public interface ProductoService {

	// Atributos de la clase

	public Producto save(Producto producto);

	// optional nos da la opcion de validar si el objeto que obtenemos de la DB
	// existe o no
	public Optional<Producto> get(Integer id);

	public void update(Producto producto);

	public void delete(Integer id);
	
	public List<Producto> findAll();



}
