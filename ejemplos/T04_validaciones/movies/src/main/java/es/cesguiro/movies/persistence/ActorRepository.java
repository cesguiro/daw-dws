package es.cesguiro.movies.persistence;

import es.cesguiro.movies.domain.entity.Actor;

public interface ActorRepository {
    int insert(Actor actor);
}
