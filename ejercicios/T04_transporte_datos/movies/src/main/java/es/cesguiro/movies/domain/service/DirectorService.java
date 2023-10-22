package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.dto.director.DirectorDetailDTO;
import es.cesguiro.movies.dto.director.DirectorInsertDTO;
import es.cesguiro.movies.dto.director.DirectorUpdateDTO;

public interface DirectorService {
    int create(Director director);
    void update(Director director);

    void delete(int id);

    Director find(int id);
}
