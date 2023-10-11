package es.cesguiro.movies.persistence.repository;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.persistence.entity.impl.DirectorEntity;

import java.util.Optional;

public interface DirectorRepository {

    Optional<DirectorEntity> findByMovieId(int movieId);
    /*int insert(Director director);

    Director find(int id);

    void update(Director director);

    void delete(int id);*/
}
