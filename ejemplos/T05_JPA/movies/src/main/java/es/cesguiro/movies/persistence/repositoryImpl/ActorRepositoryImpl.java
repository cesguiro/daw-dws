package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.repository.ActorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorRepositoryImpl implements ActorRepository {


    @Override
    public int insert(Actor actor) {
        return 0;
    }

    @Override
    public Optional<Actor> find(int id) {
        return Optional.empty();
    }

    @Override
    public List<Actor> findByMovieId(int movieId) {
        return null;
    }

    @Override
    public void update(Actor actor) {

    }

    @Override
    public void delete(int id) {

    }
}
