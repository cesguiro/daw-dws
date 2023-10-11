package es.cesguiro.movies.domain.mapper;

import es.cesguiro.movies.controller.DTO.actor.ActorDetailDTO;
import es.cesguiro.movies.controller.DTO.actor.ActorListDTO;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.persistence.entity.impl.ActorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorMapper mapper = Mappers.getMapper(ActorMapper.class);

    Actor toModel(ActorEntity entity);
    ActorDetailDTO toDetailDTO(Actor actor);
    ActorListDTO toListDTO(Actor actor);
    //MovieListDTO toListDTO(Movie movie);
}
