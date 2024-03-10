package br.com.siswbrasil.escritorio.repository;

import br.com.siswbrasil.escritorio.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>,BaseRepository<User, Long> {

	public User findByEmail(String email) {
		return find("email", email).firstResult();
	}

	public User findByUsername(String username) {
		return find("name", username).firstResult();
	}

}
