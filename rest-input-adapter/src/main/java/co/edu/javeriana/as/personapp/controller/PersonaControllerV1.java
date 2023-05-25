package co.edu.javeriana.as.personapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import co.edu.javeriana.as.personapp.adapter.PersonaInputAdapterRest;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/persona")
public class PersonaControllerV1 {
	
	@Autowired
	private PersonaInputAdapterRest personaInputAdapterRest;
	
	@ResponseBody
	@GetMapping(path = "/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonaResponse> personas(@PathVariable String database) {
		log.info("Into personas REST API");
			return personaInputAdapterRest.historial(database.toUpperCase());
	}

	//POST NEW PERSONA
	@ResponseBody
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PersonaResponse crearPersona(@RequestBody PersonaRequest request) {
		log.info("esta en el metodo crearTarea en el controller del api");
		return personaInputAdapterRest.crearPersona(request);
	}

	//PUT PERSONA BY ID
	@ResponseBody
	@PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PersonaResponse updatePersonaById(@RequestBody PersonaRequest request) {
		log.info("Into updatePersonaById REST API");
		return personaInputAdapterRest.editarPersona(request);
	}

	//GET PERSONA BY ID
	@ResponseBody
	@GetMapping(path = "/{database}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonaResponse personaById(@PathVariable String database, @PathVariable String id) {
		log.info("Into personaById REST API");
		return personaInputAdapterRest.buscarPersona(database, id);
	}

	//DELETE PERSONA BY ID
	@ResponseBody
	@DeleteMapping(path = "/{database}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonaResponse deletePersonaById(@PathVariable String database, @PathVariable String id) {
		log.info("Into deletePersonaById REST API");
		return personaInputAdapterRest.eliminarPersona(database, id);
	}
}
