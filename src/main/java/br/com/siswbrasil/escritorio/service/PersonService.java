package br.com.siswbrasil.escritorio.service;

import br.com.siswbrasil.escritorio.dao.PersonDAO;
import br.com.siswbrasil.escritorio.entity.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PersonService {

	@Inject
	PersonDAO dao;

	public Person findById(Long id) {
		var entity = dao.find(id);
		if (entity == null) {
			throw new NotFoundException();
		}
		return entity;
	}

	@Transactional
	public Person create(Person form) {
		try {
			form.setId(null);
			return dao.create(form);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalServerErrorException();
		}
	}

	@Transactional
	public Person update(Long id, Person form) {
		var entity = findById(id);
		entity.setName(form.getName());
		return dao.update(entity);
	}

	@Transactional
	public void deleteById(Long id) {
		var entity = findById(id);
		dao.remove(entity);
	}

}
