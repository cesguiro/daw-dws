package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.service.ActorService;
import es.cesguiro.movies.persistence.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {
    @Override
    public int create(Actor actor) {
        return 0;
    }

    /*@Autowired
    ActorRepository actorRepository;
    @Override
    public int create(Actor actor) {
        return actorRepository.insert(actor);
    }*/
}
