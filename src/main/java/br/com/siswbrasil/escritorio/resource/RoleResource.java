package br.com.siswbrasil.escritorio.resource;

import java.util.List;

import br.com.siswbrasil.escritorio.entity.Role;
import br.com.siswbrasil.escritorio.repository.RoleRepository;
import br.com.siswbrasil.escritorio.service.PersonService;
import br.com.siswbrasil.escritorio.service.RoleService;
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
@Path("/api/roles")
public class RoleResource {

	@Inject
	private RoleRepository repository;

	@Inject
	private RoleService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Role> findAll() {
		return repository.listAll();
	}

	@GET
	@Path("/{id}")
	public Role findById(@PathParam("id") Long id) {
		return service.findById(id);
	}

	@POST
	public Role create(@Valid Role form) {
		return service.create(form);
	}

	@PUT
	@Path("/{id}")
	public Role update(@PathParam("id") Long id,@Valid Role form) {
		return service.update(id, form);
	}

	@DELETE
	@Path("/{id}")
	public void deleteById(@PathParam("id") Long id) {
		service.deleteById(id);
	}

}
