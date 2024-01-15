package es.dsrroma.school.springboot.reuniones.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import es.dsrroma.school.springboot.reuniones.models.Persona;

@RepositoryRestResource(path = "personas") // cambia el nombre de la entidad en la url api/rest 
public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
