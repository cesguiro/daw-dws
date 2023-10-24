package es.cesguiro.movies.domain.repository;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.dto.DirectorDTO;

import java.util.Optional;

public interface DirectorRepository {
    int insert(DirectorDTO directorDTO);
    Optional<DirectorDTO> find(int id);

    void update(DirectorDTO directorDTO);

    void delete(int id);

    Optional<DirectorDTO> findByMovieId(int movieId);
}
