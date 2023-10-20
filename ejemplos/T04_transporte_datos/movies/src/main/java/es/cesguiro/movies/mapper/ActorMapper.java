package es.cesguiro.movies.mapper;


import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.dto.actor.ActorListDTO;
import es.cesguiro.movies.persistence.model.ActorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorMapper mapper = Mappers.getMapper(ActorMapper.class);

    ActorEntity toEntity(Actor actor);
}
