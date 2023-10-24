package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.dto.DirectorDTO;

public interface DirectorService {
    int create(DirectorDTO directorDTO);
    void update(DirectorDTO directorDTO);

    void delete(int id);

    DirectorDTO find(int id);
}
