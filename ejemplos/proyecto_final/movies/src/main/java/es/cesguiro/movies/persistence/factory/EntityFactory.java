package es.cesguiro.movies.persistence.factory;

import es.cesguiro.movies.persistence.entity.GenericEntity;
import es.cesguiro.movies.persistence.entity.impl.DirectorEntity;
import es.cesguiro.movies.persistence.entity.impl.MovieEntity;
import org.springframework.stereotype.Component;

public interface EntityFactory<T extends GenericEntity> {

    T createEntity();
}

/*@Component
public class EntityFactory {

    private static EntityFactory instance;

    private EntityFactory() {

    }

    public static EntityFactory getInstance() {
        if(instance == null) {
            instance = new EntityFactory();
        }
        return instance;
    }

    public MovieEntity createMovieEntity() {
        return new MovieEntity();
    }

    public DirectorEntity createDirectorEntity() {
        return new DirectorEntity();
    }
}*/
