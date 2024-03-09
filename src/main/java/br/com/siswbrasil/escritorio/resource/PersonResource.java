package br.com.siswbrasil.escritorio.resource;

import java.util.List;

import br.com.siswbrasil.escritorio.dao.PersonDAO;
import br.com.siswbrasil.escritorio.entity.Person;
import br.com.siswbrasil.escritorio.service.PersonService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/persons")
public class PersonResource {

	@Inject
	private PersonDAO dao;

	@Inject
	private PersonService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> findAll() {
		return dao.findAll();
	}

	@GET
	@Path("/{id}")
	public Person findById(@PathParam("id") Long id) {
		return service.findById(id);
	}

	@POST
	public Person create(@Valid Person form) {
		return service.create(form);
	}

	@PUT
	@Path("/{id}")
	public Person update(@PathParam("id") Long id,@Valid Person form) {
		return service.update(id, form);
	}

	@DELETE
	@Path("/{id}")
	public void deleteById(@PathParam("id") Long id) {
		service.deleteById(id);
	}

}
