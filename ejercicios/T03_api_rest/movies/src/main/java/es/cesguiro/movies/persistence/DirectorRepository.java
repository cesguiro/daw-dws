package es.cesguiro.movies.persistence;

import es.cesguiro.movies.domain.entity.Director;

public interface DirectorRepository {
    int insert(Director director);
}
