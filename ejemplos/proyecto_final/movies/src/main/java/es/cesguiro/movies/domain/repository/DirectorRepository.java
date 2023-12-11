package es.cesguiro.movies.domain.repository;

import es.cesguiro.movies.domain.entity.Director;

import java.util.Optional;

public interface DirectorRepository {

    Optional<Director> find(int id);

    Director save(Director director);

    void delete(Director director);

}
