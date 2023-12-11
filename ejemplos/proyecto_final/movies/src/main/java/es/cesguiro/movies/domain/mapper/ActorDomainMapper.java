package es.cesguiro.movies.domain.mapper;

import es.cesguiro.movies.common.dto.ActorDto;
import es.cesguiro.movies.domain.entity.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ActorDomainMapper {
    ActorDomainMapper mapper = Mappers.getMapper(ActorDomainMapper.class);

    Actor toActor(ActorDto actorDto);

    ActorDto toActorDto(Actor actor);

    void updateActorFromActorDto(ActorDto actorDto, @MappingTarget Actor actor);

}
