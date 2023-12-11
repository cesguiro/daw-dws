package es.cesguiro.movies.domain.repository;

import es.cesguiro.movies.domain.entity.Actor;

import java.util.Optional;

public interface ActorRepository {

    Optional<Actor> find(int id);

    Actor save(Actor actor);

    void delete(Actor actor);

    //Actor findByCharacterId(int characterId);
}
