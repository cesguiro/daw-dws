package es.cesguiro.movies.persistence.DAO;

import es.cesguiro.movies.persistence.entity.impl.ActorEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ActorDAO extends GenericDAO<ActorEntity, Integer> {
    Optional<List<ActorEntity>> findByMovieId(int movieId);

}
