package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.dto.director.DirectorDetailDTO;
import es.cesguiro.movies.dto.director.DirectorInsertDTO;
import es.cesguiro.movies.dto.director.DirectorUpdateDTO;

public interface DirectorService {
    int create(DirectorInsertDTO directorInsertDTO);
    void update(DirectorUpdateDTO directorUpdateDTO);

    void delete(int id);

    DirectorDetailDTO find(int id);
}
