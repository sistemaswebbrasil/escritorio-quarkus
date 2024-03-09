package br.com.siswbrasil.escritorio.dao;

import br.com.siswbrasil.escritorio.entity.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class PersonDAO extends GenericDAO<Person,Long>{

	public PersonDAO() {
		super(Person.class);
	}

	@Inject
	EntityManager em;	

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
