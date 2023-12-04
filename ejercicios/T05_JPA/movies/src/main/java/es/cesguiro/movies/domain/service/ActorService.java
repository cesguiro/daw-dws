package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.domain.entity.Actor;

public interface ActorService {
    int create(Actor actor);
    void update(Actor actor);

    void delete(int id);
}
