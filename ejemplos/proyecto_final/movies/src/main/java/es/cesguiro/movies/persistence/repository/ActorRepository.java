package es.cesguiro.movies.persistence.repository;

import es.cesguiro.movies.persistence.entity.impl.ActorEntity;

import java.util.List;
import java.util.Optional;

public interface ActorRepository {

    Optional<List<ActorEntity>> findByMovieId(int movieId);
    //int insert(Actor actor);
}
