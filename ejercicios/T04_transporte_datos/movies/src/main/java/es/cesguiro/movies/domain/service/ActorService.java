package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.Director;

public interface ActorService {
    int create(Actor actor);
    void update(Actor actor);

    void delete(int id);
    Actor find(int id);
}
