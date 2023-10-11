package es.cesguiro.movies.persistence.factory.impl;

import es.cesguiro.movies.persistence.entity.impl.MovieEntity;
import es.cesguiro.movies.persistence.factory.EntityFactory;
import org.springframework.stereotype.Component;

@Component
public class MovieFactory implements EntityFactory<MovieEntity> {

    @Override
    public MovieEntity createEntity() {
        return new MovieEntity();
    }
}
