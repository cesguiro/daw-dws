package es.cesguiro.movies.persistence.factory.impl;

import es.cesguiro.movies.persistence.entity.impl.DirectorEntity;
import es.cesguiro.movies.persistence.factory.EntityFactory;

public class DirectorFactory implements EntityFactory<DirectorEntity> {

    @Override
    public DirectorEntity createEntity() {
        return new DirectorEntity();
    }
}
