package es.dsrroma.school.springboot.reuniones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.dsrroma.school.springboot.reuniones.Services.ReunionService;

@Controller
@RequestMapping("/reuniones")
public class ReunionController {

	@Autowired
	// de no colocar el autowired el servicio nunca seria inicializado por lo que
	// daria error. por lo que crea una instancia del servicio.
	private ReunionService reunionService;

	@GetMapping
	public String getAllReuniones(Model model) {
		model.addAttribute("reuniones", reunionService.getAllReuniones());

		return "reuniones";

	}

}
