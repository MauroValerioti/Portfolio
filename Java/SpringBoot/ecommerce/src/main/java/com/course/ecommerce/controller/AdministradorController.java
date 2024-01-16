package com.course.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.course.ecommerce.model.Producto;
import com.course.ecommerce.service.ProductoService;


@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private ProductoService productoService;
	
	@GetMapping("")
	public String home( Model model) {//el model es para mandar los productos a la vista home
		
		//hacemos la peticion de la lista de productos
		List<Producto> productos= productoService.findAll();
		//paso los productos a la vista home
		model.addAttribute("productos", productos);
		return "administrador/home";
	}
	
	
}
