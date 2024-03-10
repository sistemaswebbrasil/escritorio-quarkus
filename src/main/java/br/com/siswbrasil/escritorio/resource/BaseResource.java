package br.com.siswbrasil.escritorio.resource;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import jakarta.validation.Valid;

public interface BaseResource<T, ID> {

	@Operation(summary = "findAll", description = "Lista todos os itens")
	List<T> findAll();

	@Operation(summary = "findById", description = "Lista registro pelo id")
	T findById(@Parameter(name = "id", description = "ID do recurso", required = true) ID id);

	@Operation(summary = "create", description = "Cria um novo registro")
	T create(@Valid T form);

	@Operation(summary = "update", description = "Atualiza o registro")
	T update(@Parameter(name = "id", description = "ID do recurso", required = true) ID id, @Valid T form);

	@Operation(summary = "deleteById", description = "Remove o registro pelo id")
	void deleteById(@Parameter(name = "id", description = "ID do recurso", required = true) ID id);

}