package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.repository.ActorRepository;
import es.cesguiro.movies.mapper.ActorMapper;
import es.cesguiro.movies.persistence.dao.ActorDAO;
import es.cesguiro.movies.persistence.model.ActorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorRepositoryImpl implements ActorRepository {

    @Autowired
    ActorDAO actorDAO;
    @Override
    public int insert(Actor actor) {
        return 0;
    }

    @Override
    public Optional<Actor> find(int id) {
        ActorEntity actorEntity = actorDAO.findById(id).orElse(null);
        if(actorEntity == null) {
            return Optional.empty();
        }
        return Optional.of(ActorMapper.mapper.toActor(actorEntity));
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
