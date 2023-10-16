package es.cesguiro.movies.dto.mapper;


import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.dto.actor.ActorListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorMapper mapper = Mappers.getMapper(ActorMapper.class);

    ActorListDTO toListDTO(Actor actor);
}
