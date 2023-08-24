package es.cesguiro.movies.persistence;

import es.cesguiro.movies.domain.entity.Actor;

public interface ActorRepository {
    void insert(Actor actor);
}
