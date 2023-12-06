package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.persistence.dao.impl.jpa.repository.ActorJpaRepository;
import es.cesguiro.movies.persistence.dao.impl.jpa.entity.ActorJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorRepositoryImpl implements es.cesguiro.movies.domain.repository.ActorRepository {

    @Autowired
    ActorJpaRepository actorJpaRepository;
    @Override
    public int insert(Actor actor) {
        return 0;
    }

    @Override
    public Optional<Actor> find(int id) {
        ActorJpaEntity actorJpaEntity = actorJpaRepository.findById(id).orElse(null);
        if(actorJpaEntity == null) {
            return Optional.empty();
        }
        //return Optional.of(ActorMapper.mapper.toActor(actorJpaEntity));
        return null;
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
