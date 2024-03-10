package br.com.siswbrasil.escritorio.resource;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.siswbrasil.escritorio.entity.User;
import br.com.siswbrasil.escritorio.repository.UserRepository;
import br.com.siswbrasil.escritorio.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Tag(name = "User", description = "Recursos de usuários")
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/users")
public class UserResource extends BaseResourceImpl<User, Long> {

	@Inject
	private UserRepository repository;

	@Inject
	private UserService service;

	@PostConstruct
	public void init() {
		super.repository = repository;
		super.service = service;
	}

}
