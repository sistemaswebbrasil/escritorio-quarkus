package br.com.siswbrasil.escritorio.repository;

import br.com.siswbrasil.escritorio.entity.Role;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoleRepository implements PanacheRepository<Role> {

}
