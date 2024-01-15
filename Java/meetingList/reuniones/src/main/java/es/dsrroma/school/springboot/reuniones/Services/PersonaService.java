package es.dsrroma.school.springboot.reuniones.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.dsrroma.school.springboot.reuniones.data.PersonaRepository;
import es.dsrroma.school.springboot.reuniones.models.Persona;

@Service
public class PersonaService {

	private final PersonaRepository personaRepository;

	// con esto instancio el repository en lugar de usar el autowired.
	public PersonaService(PersonaRepository personaRepository) {
		this.personaRepository = personaRepository;
	}

	// este service luego sera llamado desde el controller
	public List<Persona> getAllPersonas() {
		return personaRepository.findAll();
	}

	public Optional<Persona> getById(long Id) {
		return personaRepository.findById(Id);
	}
	
//	public Persona getById(long id) {
//	    return personaRepository.findById(id)
//	            .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + id));
//	}


}
