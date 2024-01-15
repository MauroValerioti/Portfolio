package es.dsrroma.school.springboot.reuniones.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.dsrroma.school.springboot.reuniones.data.ReunionRepository;
import es.dsrroma.school.springboot.reuniones.models.Reunion;

@Service
public class ReunionService {

	private final ReunionRepository reunionRepository;

	// en lugar de autowired puedo instanciar el repository
	public ReunionService(ReunionRepository reunionRepository) {
		this.reunionRepository = reunionRepository;
	}

	// uso el metodo findAll que ya me provee la implementacion de JpaRepository
	public List<Reunion> getAllReuniones() {
		return reunionRepository.findAll();
	}
	
	public Optional<Reunion> getById(long id) {
		return reunionRepository.findById(id);
	}
}
