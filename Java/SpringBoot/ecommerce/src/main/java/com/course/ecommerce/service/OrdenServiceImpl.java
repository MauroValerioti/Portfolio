package com.course.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.ecommerce.model.Orden;
import com.course.ecommerce.model.Usuario;
import com.course.ecommerce.repository.IOrdenRepository;

@Service
public class OrdenServiceImpl implements IOrdenService{

	@Autowired
	private IOrdenRepository ordenRepository;
	
	
	@Override
	public Orden save(Orden orden) {
		return ordenRepository.save(orden);
	}


	@Override
	//obtiene todas las ordenes.
	public List<Orden> findAll() {
		return ordenRepository.findAll();
	}

	public String generarNroOrden() {
		
		int numero = 0;
		String nroConcatenado = "";
		
		List<Orden> ordenes = findAll(); //para obtener todas las ordenes.
		
		List<Integer> numeros = new ArrayList<Integer>();
		
		ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero()))); //convierto cada numero de la lista de ordenes de string a integer.
		
		if (ordenes.isEmpty()) {
			numero = 1;
		}else {
			numero = numeros.stream().max(Integer::compare).get(); //obtengo el mayor numero de la lista de ordenes
			numero++;
		}
		
		if (numero<10) { //genera el formato de orden para que tenga varios ceros adelante.
			nroConcatenado = "000000000" + String.valueOf(numero);
		}else if (numero<100) {
			nroConcatenado = "00000000" + String.valueOf(numero);	
		}else if (numero<1000) {
			nroConcatenado = "0000000" + String.valueOf(numero);
		}else if (numero<10000) {
			nroConcatenado = "000000" + String.valueOf(numero);
		}
		
		return nroConcatenado;
	}


	@Override
	public List<Orden> findByUsuario(Usuario usuario) {

		
		return ordenRepository.findByUsuario(usuario);
	}


	@Override
	public Optional<Orden> findById(Integer id) {
		
		return ordenRepository.findById(id);
	}
	
}
