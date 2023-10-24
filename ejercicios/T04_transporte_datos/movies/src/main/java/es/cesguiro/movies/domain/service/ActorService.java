package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.dto.ActorDTO;

public interface ActorService {
    int create(ActorDTO actorDTO);
    void update(ActorDTO actorDTO);

    void delete(int id);
    ActorDTO find(int id);
}
