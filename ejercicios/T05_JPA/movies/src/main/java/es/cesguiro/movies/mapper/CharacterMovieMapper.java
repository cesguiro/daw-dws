package es.cesguiro.movies.mapper;

import es.cesguiro.movies.controller.model.character.CharacterMovieCreateWeb;
import es.cesguiro.movies.controller.model.character.CharacterMovieListWeb;
import es.cesguiro.movies.controller.model.character.CharacterMovieUpdateWeb;
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

    @Mapping(target = "actor", expression = "java(ActorMapper.mapper.toActor(actorId))")
    CharacterMovie toCharacterMovie(int actorId, String characters);

    @Named("mapActorIdtoActor")
    default List<CharacterMovie> mapCharactersMoviesCreateWebsToCharacterMovies(List<CharacterMovieCreateWeb> characterMovieCreateWebs) {
        return characterMovieCreateWebs.stream()
                .map(characterMovieCreateWeb -> CharacterMovieMapper.mapper.toCharacterMovie(
                        characterMovieCreateWeb.getActorId(),
                        characterMovieCreateWeb.getCharacters()))
                .toList();
    }

    //List<CharacterMovie> toCharacterMovies(List<CharacterMovieCreateWeb> characterMovieCreateWebs);

    @Mapping(target = "actor", expression = "java(ActorMapper.mapper.toActor(characterMovieEntity.getActorEntity()))")
    CharacterMovie toCharacterMovie(CharacterMovieEntity characterMovieEntity);

    List<CharacterMovie> toCharacterMovies(List<CharacterMovieEntity> characterMovieEntities);

    @Mapping(target = "actorEntity", expression = "java(ActorMapper.mapper.toActorEntity(characterMovie.getActor()))")
    CharacterMovieEntity toCharacterMovieEntity(CharacterMovie characterMovie);

    List<CharacterMovieEntity> toCharacterMovieEntities(List<CharacterMovie> characterMovies);

    @Mapping(target = "actorId", expression="java(characterMovie.getActor().getId())")
    @Mapping(target = "actorName", expression="java(characterMovie.getActor().getName())")
    CharacterMovieListWeb toCharacterMovieListWeb(CharacterMovie characterMovie);

    List<CharacterMovieListWeb> toCharacterMovieListWeb(List<CharacterMovie> characterMovies);

    @Mapping(target = "actor", expression = "java(ActorMapper.mapper.toActor(characterMovieCreateWeb.getActorId()))")
    CharacterMovie toCharacterMovie(CharacterMovieCreateWeb characterMovieCreateWeb);

    @Mapping(target = "actor", expression = "java(ActorMapper.mapper.toActor(characterMovieUpdateWeb.getActorId()))")
    CharacterMovie toCharacterMovie(CharacterMovieUpdateWeb characterMovieUpdateWeb);

}
