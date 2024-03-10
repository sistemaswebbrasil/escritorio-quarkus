package br.com.siswbrasil.escritorio.resource;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;

import br.com.siswbrasil.escritorio.repository.BaseRepository;
import br.com.siswbrasil.escritorio.service.BaseService;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public abstract class BaseResourceImpl<T, ID> implements BaseResource<T, ID> {

	protected BaseRepository<T, ID> repository;

	protected BaseService<T, ID> service;

	@Operation(summary = "findAll", description = "Lista todos os itens")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<T> findAll() {
		return repository.listAll();
	}

	@Operation(summary = "findById", description = "Lista registro pelo id")
	@GET
	@Path("/{id}")
	@Override
	public T findById(@PathParam("id") ID id) {
		return service.findById(id);
	}

	@Operation(summary = "create", description = "Cria um novo registro")
	@POST
	@Override
	public T create(@Valid T form) {
		return service.create(form);
	}

	@Operation(summary = "update", description = "Atualiza o registro")
	@PUT
	@Path("/{id}")
	@Override
	public T update(@PathParam("id") ID id, @Valid T form) {
		return service.update(id, form);
	}

	@Operation(summary = "deleteById", description = "Remove pelo id")
	@DELETE
	@Path("/{id}")
	@Override
	public void deleteById(@PathParam("id") ID id) {
		service.deleteById(id);
	}

}
