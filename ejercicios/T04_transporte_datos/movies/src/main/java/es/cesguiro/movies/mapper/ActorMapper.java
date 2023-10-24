package es.cesguiro.movies.mapper;


import es.cesguiro.movies.controller.model.actor.ActorCreateWeb;
import es.cesguiro.movies.controller.model.actor.ActorDetailWeb;
import es.cesguiro.movies.controller.model.actor.ActorListWeb;
import es.cesguiro.movies.controller.model.actor.ActorUpdateWeb;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.dto.ActorDTO;
import es.cesguiro.movies.persistence.model.ActorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorMapper mapper = Mappers.getMapper(ActorMapper.class);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java(resultSet.getInt(\"deathYear\"))")
    ActorEntity toActorEntity(ResultSet resultSet) throws SQLException;

    ActorDTO toActorDTO(ActorEntity actorEntity);
    ActorListWeb toActorListWeb(ActorDTO actorDTO);

    ActorDetailWeb toActorDetailWeb(ActorDTO actorDTO);

    Actor toActor(ActorDTO actorDTO);

    ActorDTO toActorDTO(ActorCreateWeb actorCreateWeb);

    ActorDTO toActorDTO(ActorUpdateWeb actorUpdateWeb);

    ActorDTO toActorDTO(Actor actor);

    ActorEntity toActorEntity(ActorDTO actorDTO);
}
