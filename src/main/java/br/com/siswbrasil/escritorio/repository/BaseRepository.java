package br.com.siswbrasil.escritorio.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface BaseRepository<T,ID> extends PanacheRepository<T> {


}
