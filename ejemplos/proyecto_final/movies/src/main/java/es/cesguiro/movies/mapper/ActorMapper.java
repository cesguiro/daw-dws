package es.cesguiro.movies.mapper;


import es.cesguiro.movies.controller.response.ActorResponse;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.Director;
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

    ActorDTO toActorDTO(ActorEntity actorEntity);

    ActorResponse toActorResponse(ActorDTO actorDTO);

    /** Antiguos **/
    ActorEntity toActorEntity(Actor actor);

    @Mapping(target = "id", source = "actorId")
    Actor toActor(Integer actorId);

    /*@Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java(resultSet.getInt(\"deathYear\"))")
    ActorEntity toActorEntity(ResultSet resultSet) throws SQLException;*/

    Actor toActor(ActorEntity actorEntity);
}
