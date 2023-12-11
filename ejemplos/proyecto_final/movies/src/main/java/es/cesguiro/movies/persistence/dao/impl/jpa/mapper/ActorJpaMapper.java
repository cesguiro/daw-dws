package es.cesguiro.movies.persistence.dao.impl.jpa.mapper;

import es.cesguiro.movies.common.dto.ActorDto;
import es.cesguiro.movies.persistence.dao.impl.jpa.entity.ActorJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ActorJpaMapper {

    ActorJpaMapper mapper = Mappers.getMapper(ActorJpaMapper.class);

    ActorDto toActorDto(ActorJpaEntity actorJpaEntity);

    ActorJpaEntity toActorEntity(ActorDto actorDto);
}
