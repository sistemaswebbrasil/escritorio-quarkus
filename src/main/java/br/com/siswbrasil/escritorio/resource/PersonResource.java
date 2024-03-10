package br.com.siswbrasil.escritorio.resource;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

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
@Tag(name = "Person", description = "Recursos de pessoas")
public class PersonResource {

	@Inject
	private PersonDAO dao;

	@Inject
	private PersonService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)

	@Operation(summary = "findAll", description = "Lista todos os itens", operationId = "PersonResource.findAll")
	public List<Person> findAll() {
		return dao.findAll();
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "findById", description = "Lista registro pelo id", operationId = "PersonResource.findById")
	public Person findById(@Parameter(name = "id", description = "ID do recurso", required = true) @PathParam("id") Long id) {
		return service.findById(id);
	}

	@POST
	@Operation(summary = "create", description = "Cria um novo registro", operationId = "PersonResource.create")
	public Person create(@Valid Person form) {
		return service.create(form);
	}

	@PUT
	@Path("/{id}")
	@Operation(summary = "update", description = "Atualiza o registro", operationId = "PersonResource.update")
	public Person update(@Parameter(name = "id", description = "ID do recurso", required = true) @PathParam("id") Long id, @Valid Person form) {
		return service.update(id, form);
	}

	@DELETE
	@Path("/{id}")
	@Operation(summary = "deleteById", description = "Remove o registro pelo id", operationId = "PersonResource.deleteById")

	public void deleteById(
			@Parameter(name = "id", description = "ID do recurso", required = true) @PathParam("id") Long id) {
		service.deleteById(id);
	}

}
