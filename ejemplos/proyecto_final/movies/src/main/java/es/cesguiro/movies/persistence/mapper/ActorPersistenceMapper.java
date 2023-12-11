package es.cesguiro.movies.persistence.mapper;

import es.cesguiro.movies.common.dto.ActorDto;
import es.cesguiro.movies.domain.entity.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ActorPersistenceMapper {

    ActorPersistenceMapper mapper = Mappers.getMapper(ActorPersistenceMapper.class);

    Actor toActor(ActorDto actorDto);

    ActorDto toActorDto(Actor actor);
}
