package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.service.ActorService;
import es.cesguiro.movies.persistence.ActorRepository;
import es.cesguiro.movies.persistence.impl.ActorRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorRepository actorRepository;
    @Override
    public void create(Actor actor) {
        actorRepository.insert(actor);
    }
}
