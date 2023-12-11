package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.common.dto.ActorDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ActorDao {
    Optional<ActorDto> find(int id);

    ActorDto save(ActorDto actorDto);

    void delete(ActorDto actorDto);

    //ActorDto findByCharacterId(int );
}
