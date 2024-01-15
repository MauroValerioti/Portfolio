package es.dsrroma.school.springboot.reuniones.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.dsrroma.school.springboot.reuniones.Services.ReunionService;
import es.dsrroma.school.springboot.reuniones.models.Reunion;

@RestController
@RequestMapping("/api/reuniones") // el /api es para que no colisiones con el /reuniones ya existente
public class ReunionesResController {

	private ReunionService reunionService;

	//esta es otra opcion en lugar de usar el @Autowired.
	public ReunionesResController(ReunionService reunionService) {
		super();
		this.reunionService = reunionService;
	}
	
	@GetMapping
	public List<Reunion> getAllReuniones(){
		
		return reunionService.getAllReuniones();
	}
	
	
}
