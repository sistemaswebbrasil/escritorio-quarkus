package br.com.siswbrasil.escritorio.resource;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.siswbrasil.escritorio.entity.Role;
import br.com.siswbrasil.escritorio.repository.RoleRepository;
import br.com.siswbrasil.escritorio.service.RoleService;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Tag(name = "Role", description = "Recursos de perfis de permissão")
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/roles")
public class RoleResource extends BaseResourceImpl<Role, Long> {
	
	@Inject
	private RoleRepository repository;

	@Inject
	private RoleService service;
	
	@PostConstruct
	public void init() {
		super.repository = repository;
		super.service = service;
	}

}
