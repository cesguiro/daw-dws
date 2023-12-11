package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.common.dto.DirectorDto;

public interface DirectorService {

    DirectorDto find(int id);

    DirectorDto create(DirectorDto directorDto);
    DirectorDto update(DirectorDto directorDto);

    void delete(int id);

}
