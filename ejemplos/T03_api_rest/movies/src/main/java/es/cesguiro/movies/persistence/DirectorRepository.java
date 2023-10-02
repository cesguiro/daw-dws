package es.cesguiro.movies.persistence;

import es.cesguiro.movies.domain.entity.Director;

import java.util.Optional;

public interface DirectorRepository {
    int insert(Director director);

    Director find(int id);

    void update(Director director);

    void delete(int id);
}
