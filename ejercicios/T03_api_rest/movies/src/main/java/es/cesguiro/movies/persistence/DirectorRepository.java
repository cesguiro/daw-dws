package es.cesguiro.movies.persistence;

import es.cesguiro.movies.domain.entity.Director;

public interface DirectorRepository {
    void insert(Director director);
}
