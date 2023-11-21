package es.cesguiro.movies.mapper;

import es.cesguiro.movies.controller.model.character.CharacterMovieListWeb;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.CharacterMovie;
import es.cesguiro.movies.persistence.model.ActorEntity;
import es.cesguiro.movies.persistence.model.CharacterMovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Mapper(componentModel = "spring")
public interface CharacterMovieMapper {

    CharacterMovieMapper mapper = Mappers.getMapper(CharacterMovieMapper.class);

    @Mapping(target = "actor", expression = "java(ActorMapper.mapper.toActor(characterMovieEntity.getActorEntity()))")
    CharacterMovie toCharacterMovie(CharacterMovieEntity characterMovieEntity);

    List<CharacterMovie> toCharacterMovies(List<CharacterMovieEntity> characterMovieEntities);

    /*@Named("actorEntityToActor")
    default Actor mapActorEntityToActor(ActorEntity actorEntity) {
        return ActorMapper.mapper.toActor(actorEntity);
    }

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "characters", expression = "java(resultSet.getString(\"characters\"))")
    CharacterMovieEntity toCharacterMovieEntity(ResultSet resultSet) throws SQLException;*/

    @Mapping(target = "actorId", expression="java(characterMovie.getActor().getId())")
    @Mapping(target = "actorName", expression="java(characterMovie.getActor().getName())")
    CharacterMovieListWeb toCharacterMovieListWeb(CharacterMovie characterMovie);

    List<CharacterMovieListWeb> toCharacterMovieListWeb(List<CharacterMovie> characterMovies);

}
