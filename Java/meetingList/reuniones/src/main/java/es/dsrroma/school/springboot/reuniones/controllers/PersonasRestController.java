package es.dsrroma.school.springboot.reuniones.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.dsrroma.school.springboot.reuniones.Services.PersonaService;
import es.dsrroma.school.springboot.reuniones.models.Persona;

@RestController
@RequestMapping("/api/personas")
public class PersonasRestController {
	
	private PersonaService personaService;

	public PersonasRestController(PersonaService personaService) {
		super();
		this.personaService = personaService;
	}
	
	@GetMapping
	public List<Persona> getAllPersonas(){
		return personaService.getAllPersonas();
	}
	
}
