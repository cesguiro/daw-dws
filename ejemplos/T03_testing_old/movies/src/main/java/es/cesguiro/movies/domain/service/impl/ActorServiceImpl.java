package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.service.ActorService;
import es.cesguiro.movies.persistence.ActorRepository;
import es.cesguiro.movies.persistence.impl.ActorRepositoryImpl;

public class ActorServiceImpl implements ActorService {

    ActorRepository actorRepository = new ActorRepositoryImpl();
    @Override
    public void create(Actor actor) {
        actorRepository.insert(actor);
    }
}
