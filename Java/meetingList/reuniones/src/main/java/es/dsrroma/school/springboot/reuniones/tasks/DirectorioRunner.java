package es.dsrroma.school.springboot.reuniones.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import es.dsrroma.school.springboot.reuniones.Services.PersonaService;

@Component
@Order(1)
public class DirectorioRunner  implements CommandLineRunner{

	private static final Logger LOG = LoggerFactory.getLogger(DirectorioRunner.class);
	
	@Autowired
	private PersonaService personaService;
	
	@Override
	//se ejecuta al finalizar la ejecucion de todo lo demas
	public void run(String... args) throws Exception {
		LOG.info("Directorio de personas: ");
		personaService.getAllPersonas().forEach(persona ->  {
				LOG.info(persona.toString());
		});
		LOG.info("Fin del directorio");
	}

}
