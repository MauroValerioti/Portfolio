package com.course.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.course.ecommerce.model.Orden;
import com.course.ecommerce.model.Usuario;
import com.course.ecommerce.service.IOrdenService;
import com.course.ecommerce.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	// esto nos permite ver por consola la salida que se valla generando de los
	// datos
	private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private IUsuarioService usuarioService; // para poder realizar el CRUD

	
	@Autowired
	private IOrdenService ordenService;
	
	// /usuario/registro
	@GetMapping("/registro")
	public String create() {

		return "usuario/registro";
	}

	@PostMapping("/save") // como voy a enviar toda la info adentro de unformulario la peticion debe ser
							// del tipo postmapping
	public String save(Usuario usuario) {

		logger.info("Usuario registro: {}", usuario); // esto me permite sacar por consola a traves del toString los
														// datos que vallan siendo ingresados
		usuario.setTipo("USER");
		usuarioService.save(usuario);
		return "redirect:/";
	}

	@GetMapping("/login")
	public String login() {

		return "usuario/login";
	}

	@PostMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session) { //el objeto session se mantiene durante toda la sesion
		logger.info("Accesos:  {}", usuario);
		
		Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());
		//logger.info("Usuario obtenido de db: {}", user.get());
		
		if (user.isPresent()) { //como el objeto es optional puedo preguntar si esta presente el usuario
			session.setAttribute("idusuario", user.get().getId());
			if(user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador"; //si es admin que me redireccione a la pagina del administrador.
			}else {
				return"redirect:/"; 
			}
		}else {
			logger.info("Usuario no existe!"); //si el usuario no esta presente
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/compras")
	public String obtenerCompras(Model model, HttpSession session) {
		
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		Usuario usuario = usuarioService.findById( Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		List<Orden> ordenes = ordenService.findByUsuario(usuario);
		
		model.addAttribute("ordenes", ordenes);
		return "usuario/compras";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
		
		logger.info("ID de la orden: {}", id);
		
		Optional<Orden> orden = ordenService.findById(id);
		
		model.addAttribute("detalles", orden.get().getDetalle());
		
		//sesion
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		
		return "usuario/detallecompra";
	}
	
	@GetMapping("/cerrar")
	public String cerrarSesion(HttpSession session) {
		
		session.removeAttribute("idusuario");
		
		return "redirect:/";
	}
	
}
