package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.persistence.dao.ActorDao;
import es.cesguiro.movies.persistence.mapper.ActorPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ActorRepositoryImpl implements es.cesguiro.movies.domain.repository.ActorRepository {

    @Qualifier("ActorDaoJpaImpl")
    final ActorDao actorDao;

    @Autowired
    public ActorRepositoryImpl(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public Optional<Actor> find(int id) {
        return Optional.ofNullable(
                ActorPersistenceMapper
                        .mapper
                        .toActor(
                                actorDao
                                        .find(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public Actor save(Actor actor) {
        return ActorPersistenceMapper
                .mapper
                .toActor(
                        actorDao
                                .save(
                                        ActorPersistenceMapper
                                                .mapper
                                                .toActorDto(actor)
                                )
                );
    }

    @Override
    public void delete(Actor actor) {
        actorDao.delete(
                ActorPersistenceMapper
                        .mapper
                        .toActorDto(actor)
        );
    }

    /*@Override
    public Actor findByCharacterId(int characterId) {
        return ActorPersistenceMapper
                .mapper
                .toActor(
                        actorDao
                                .findByCharacterId(characterId)
                );
    }*/
}
