package com.course.ecommerce.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.course.ecommerce.model.Producto;
import com.course.ecommerce.model.Usuario;
import com.course.ecommerce.service.IUsuarioService;
import com.course.ecommerce.service.ProductoService;
import com.course.ecommerce.service.UploadFileService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	// este atributo me permite ver como INFO los productos que voy cargando en la
	// consola del IDE
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

	@Autowired // con el @AutoWired spring se encarga de instanciar la variable no es
				// neceesario que lo hagamos nosotros
	private ProductoService productoService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private UploadFileService upload;

	@GetMapping("") // las comillas vacias van para que se mapee directamente hacia productos
	public String show(Model model) { // model traera el elemento del backend hacia la vista
		// contiene 2 parametros, el primer es el nombre con el que traere la info y el
		// 2do es los elementos que voy a recibir
		model.addAttribute("productos", productoService.findAll());

		return "productos/show";
	}

	@GetMapping("/create")
	public String create() {
		return "productos/create";
	}

	// img es el atributo que tenemos en create.html con id="img
	@PostMapping("/save")
	public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session)
			throws IOException {
		LOGGER.info("Este es el objeto producto {}", producto);

		// obtengo el usuario de la session
		Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString() )).get();

		producto.setUsuario(u);

		// imagen
		if (producto.getId() == null) { // esta validacion es cuando se crea un producto.
			String nombreImagen = upload.saveImage(file);
			producto.setImagen(nombreImagen);
		} else {
		}

		productoService.save(producto);
		return "redirect:/productos";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) { // @PathVariable nos permite mapear la variable que
																// viene a la url.
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = productoService.get(id); // esto es lo que devuelve cuando hago la
																		// consulta por un elemento de tipo producto.
		producto = optionalProducto.get();

		LOGGER.info("Producto buscado: {}", producto); // el LOGGER me permitira ver en la consola la variable que va a
														// venir dentro de producto.
		model.addAttribute("producto", producto);

		return "productos/edit";
	}

	@PostMapping("/update")
	public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {

		Producto p = new Producto(); // instancio el producto
		p = productoService.get(producto.getId()).get(); // obtengo la imagen que tenia

		if (file.isEmpty()) { // este es el caso cuando editamos el producto pero no cambiamos la imagen.

			producto.setImagen(p.getImagen());
		} else { // en el caso de que quiera cambiar o editar la imagen existente

			// eliminar para cuando no sea la imagen por defecto.
			if (p.getImagen().equals("default.jpg")) {
				upload.deleteImagen(p.getImagen());
			}

			String nombreImagen = upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}

		producto.setUsuario(p.getUsuario());
		productoService.update(producto);
		return "redirect:/productos";
	}

	// tengo que obtener el id al cual voy a mapearlo antes de borarlo
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		Producto p = new Producto();
		p = productoService.get(id).get();

		// eliminar para cuando no sea la imagen por defecto.
		if (p.getImagen().equals("default.jpg")) {
			upload.deleteImagen(p.getImagen());
		}

		productoService.delete(id);
		return "redirect:/productos";
	}

}
