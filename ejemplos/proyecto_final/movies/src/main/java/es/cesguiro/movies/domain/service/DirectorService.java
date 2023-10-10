package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.domain.entity.Director;

public interface DirectorService {
    int create(Director director);

    void update(int id, Director director);

    void delete(int id);
}
