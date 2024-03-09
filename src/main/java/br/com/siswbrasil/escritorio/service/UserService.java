package br.com.siswbrasil.escritorio.service;

import br.com.siswbrasil.escritorio.entity.User;
import br.com.siswbrasil.escritorio.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UserService {

	@Inject
	private UserRepository repository;

	public User findById(Long id) {
		var entity = repository.findById(id);
		if (entity == null) {
			throw new NotFoundException();
		}
		return entity;
	}

	@Transactional
	public User create(User form) {
		try {
			form.setId(null);
			repository.persist(form);
			return form;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalServerErrorException();
		}
	}

	@Transactional
	public User update(Long id, User form) {
		var entity = findById(id);
		entity.setName(form.getName());
		repository.persist(form);
		return entity;
	}

	@Transactional
	public void deleteById(Long id) {
		var entity = findById(id);
		repository.delete(entity);
	}

}
