package es.cesguiro.movies.persistence.dao.impl.jpa;

import es.cesguiro.movies.common.dto.ActorDto;
import es.cesguiro.movies.persistence.dao.ActorDao;
import es.cesguiro.movies.persistence.dao.impl.jpa.mapper.ActorJpaMapper;
import es.cesguiro.movies.persistence.dao.impl.jpa.repository.ActorJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActorDaoJpaImpl implements ActorDao {

    final ActorJpaRepository actorJpaRepository;

    @Autowired
    public ActorDaoJpaImpl(ActorJpaRepository actorJpaRepository) {
        this.actorJpaRepository = actorJpaRepository;
    }
    @Override
    public Optional<ActorDto> find(int id) {
        return Optional.ofNullable(
                ActorJpaMapper
                        .mapper
                        .toActorDto(
                                actorJpaRepository
                                        .findById(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public ActorDto save(ActorDto actorDto) {
        return ActorJpaMapper
                .mapper
                .toActorDto(
                        actorJpaRepository
                                .save(
                                        ActorJpaMapper
                                                .mapper
                                                .toActorEntity(actorDto)
                                )
                );
    }

    @Override
    public void delete(ActorDto actorDto) {
        actorJpaRepository
                .delete(
                        ActorJpaMapper
                                .mapper
                                .toActorEntity(actorDto)
                );
    }

    /*@Override
    public ActorDto findByCharacterId(int characterId) {
        return ActorJpaMapper
                .mapper
                .toActorDto(
                        actorJpaRepository
                                .findByCharacterId(characterId)
                );
    }*/
}
