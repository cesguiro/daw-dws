package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.common.dto.ActorDto;

public interface ActorService {

    ActorDto find(int id);

    ActorDto create(ActorDto actorDto);
    ActorDto update(ActorDto actorDto);

    void delete(int id);
}
