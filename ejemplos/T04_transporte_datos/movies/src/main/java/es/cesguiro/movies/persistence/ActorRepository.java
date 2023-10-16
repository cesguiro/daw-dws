package es.cesguiro.movies.persistence;

import es.cesguiro.movies.domain.entity.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorRepository {
    int insert(Actor actor);
    Optional<Actor> find(int id);

    List<Actor> findByMovieId(int movieId);

    void update(Actor actor);

    void delete(int id);
}
