package com.course.ecommerce.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.course.ecommerce.model.DetalleOrden;
import com.course.ecommerce.model.Orden;
import com.course.ecommerce.model.Producto;
import com.course.ecommerce.model.Usuario;
import com.course.ecommerce.service.IDetalleOrdenService;
import com.course.ecommerce.service.IOrdenService;
import com.course.ecommerce.service.IUsuarioService;
import com.course.ecommerce.service.ProductoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/") // el '/' implica que va a apuntar a la raiz de la pagina. este es mi home
public class HomeController {

	// creo esta variable para que me tome la impresion por pantalla de las
	// variables que sean enviadas.
	private final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired // la notacion autowired es para que inyecte el contenedor del framework
	private ProductoService productoService;

	@Autowired // hago esto par aobtener el usuario luego y mandarlo a la vista
	private IUsuarioService usuarioService;

	@Autowired
	private IOrdenService ordenService; // para persisistir en la base de datos

	@Autowired
	private IDetalleOrdenService detalleOrdenService; // para persisistir en la base de datos

	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

	// datos de la orden
	Orden orden = new Orden();

	@GetMapping("") // este metodo getMapping va a responder unicamente a la raiz
	public String home(Model model, HttpSession session) {

		log.info("Sesion del usuario: {}", session.getAttribute("idusuario")); // esto nos permitira ver el usuario que
																				// esta logueado en la sesion.
		model.addAttribute("productos", productoService.findAll());

		// session
		model.addAttribute("sesion", session.getAttribute("idusuario"));

		return "usuario/home"; // lo que hago aca es apuntar a la carpeta usuario y dentro de ella al archivo
								// home
	}

	@GetMapping("productohome/{id}") // aca le digo que este metodo va a responder a peticiones del tipo GetMapping y
										// va a ir a buscar el id del producto.
	public String productoHome(@PathVariable Integer id, Model model) { // con la noteacion @PathVariable el framework
																		// se da cuenta de que este valor id va a ser
																		// tomado de la url. //el objeto de tipo model
																		// nos permite llevar info a la vista.
		log.info("Id producto enviado por parametro {} ", id);

		Producto producto = new Producto();
		Optional<Producto> productoOptional = productoService.get(id);
		producto = productoOptional.get();

		model.addAttribute("producto", producto); // el 1er atributo es el nombre que tendra en la vista y el 2do es la
													// variable que le paso.

		return "usuario/productohome";
	}

	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) { // estos parametros
																									// son para ir a
																									// buscar del
																									// producto el id
																									// que le correponde
																									// y la cantidad a
																									// comprar.
		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		double sumaTotal = 0;

		Optional<Producto> optionalProducto = productoService.get(id);
		log.info("producto anadido:  {}", optionalProducto.get());
		log.info("cantidad: {}", cantidad);

		producto = optionalProducto.get();

		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombre());
		detalleOrden.setTotal(producto.getPrecio() * cantidad);
		detalleOrden.setProducto(producto); // producto al que esta vinculado la orden

		// validar que el producto no se pueda agregar mas de una vez.
		Integer idProducto = producto.getId();
		boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto); // funcion lambda de
																									// la API Java 8

		if (!ingresado) {
			detalles.add(detalleOrden);
		}

		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum(); // funcion lambda para sumar todos los
																				// productos que esten en la lista
		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles); // agrega todos los detalles que va pasando el usuario
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}

	// quitar un producto del carrito
	@GetMapping("delete/cart/{id}")
	public String deleteProductCart(@PathVariable Integer id, Model model) {

		List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();

		for (DetalleOrden detalleOrden : detalles) {
			if (detalleOrden.getProducto().getId() != id) {
				ordenesNueva.add(detalleOrden); // crea una nueva lista sin el id producto que se va a quitar.
			}
		}

		// poner la nueva lista con los productos restantes
		detalles = ordenesNueva;

		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum(); // funcion lambda para sumar todos los
																				// productos que esten en la lista

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles); // agrega todos los detalles que va pasando el usuario
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}

	@GetMapping("/getCart")
	public String getcart(Model model, HttpSession session) {

		model.addAttribute("cart", detalles); // agrega todos los detalles que va pasando el usuario
		model.addAttribute("orden", orden); // detalles y orden son atributos globales.

		// sesion
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "/usuario/carrito";
	}

	@GetMapping("/order")
	public String order(Model model, HttpSession session) {

		Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get(); // como
																															// retorna
																															// un
																															// optional
																															// tengo
																															// que
																															// hacerlo
																															// con
																															// get

		model.addAttribute("cart", detalles); // agrega todos los detalles que va pasando el usuario
		model.addAttribute("orden", orden); // detalles y orden son atributos globales.
		model.addAttribute("usuario", usuario);

		return "usuario/resumenorden";
	}

	@GetMapping("/saveOrder") // este mismo nombre tiene que ir luego en la plantilla.
	// metodo para guardar la orden
	public String saveOrder(HttpSession session) {
		Date fechaCreacion = new Date(); // para guardar la fecha en la que se creo la orden.
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(ordenService.generarNroOrden());

		// usuario logueado que esta haciendo la orden. queda pendiente el login aun.
		Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get(); // como
																															// retorna
																															// un
																															// optional
																															// tengo
																															// que
																															// hacerlo
																															// con
																															// get

		orden.setUsuario(usuario); // seteo el usuario en la orden.
		ordenService.save(orden); // persisto la orden en la db con el metodo save.

		// guardar detalles
		for (DetalleOrden dt : detalles) {
			dt.setOrden(orden);
			detalleOrdenService.save(dt);
		}

		// limpieza de la lista y orden.
		orden = new Orden();
		detalles.clear();

		return "redirect:/";
	}

	@PostMapping("/search") // permite realizar la busqueda en el buscador en base a palabras y
							// coincidencias
	public String searchProduct(@RequestParam String nombre, Model model) {
		log.info("nombre del producto: ", nombre);

		// paso una lista de los productos que tienen el valor buscado
		List<Producto> productos = productoService.findAll().stream().filter(p -> p.getNombre().contains(nombre))
				.collect(Collectors.toList());

		model.addAttribute("productos", productos); // le paso al template en thymeleaf la lista de productos.

		return "usuario/home";
	}

}
