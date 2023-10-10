package es.cesguiro.movies.persistence.factory.impl;

import es.cesguiro.movies.persistence.entity.impl.ActorEntity;
import es.cesguiro.movies.persistence.factory.EntityFactory;

public class ActorFactory implements EntityFactory<ActorEntity> {

    @Override
    public ActorEntity createEntity() {
        return new ActorEntity();
    }

}
