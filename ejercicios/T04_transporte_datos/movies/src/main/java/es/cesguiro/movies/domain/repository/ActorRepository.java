package es.cesguiro.movies.domain.repository;

import es.cesguiro.movies.dto.ActorDTO;

import java.util.List;
import java.util.Optional;

public interface ActorRepository {
    int insert(ActorDTO actorDTO);
    Optional<ActorDTO> find(int id);

    List<ActorDTO> findByMovieId(int movieId);

    void update(ActorDTO actorDTO);

    void delete(int id);
}
