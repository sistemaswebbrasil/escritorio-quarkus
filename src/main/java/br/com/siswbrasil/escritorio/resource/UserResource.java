package br.com.siswbrasil.escritorio.resource;

import java.util.List;

import br.com.siswbrasil.escritorio.entity.User;
import br.com.siswbrasil.escritorio.repository.UserRepository;
import br.com.siswbrasil.escritorio.service.UserService;
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
@Path("/api/users")
public class UserResource {

	@Inject
	private UserRepository repository;

	@Inject
	private UserService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> findAll() {
		return repository.listAll();
	}

	@GET
	@Path("/{id}")
	public User findById(@PathParam("id") Long id) {
		return service.findById(id);
	}

	@POST
	public User create(@Valid User form) {
		return service.create(form);
	}

	@PUT
	@Path("/{id}")
	public User update(@PathParam("id") Long id,@Valid User form) {
		return service.update(id, form);
	}

	@DELETE
	@Path("/{id}")
	public void deleteById(@PathParam("id") Long id) {
		service.deleteById(id);
	}

}
