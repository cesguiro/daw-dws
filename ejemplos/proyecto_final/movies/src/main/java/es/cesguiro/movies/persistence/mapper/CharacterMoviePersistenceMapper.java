package es.cesguiro.movies.persistence.mapper;

import es.cesguiro.movies.common.dto.CharacterMovieDto;
import es.cesguiro.movies.domain.entity.CharacterMovie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacterMoviePersistenceMapper {

    CharacterMoviePersistenceMapper mapper = Mappers.getMapper(CharacterMoviePersistenceMapper.class);

    @Mapping(target = "actor", expression = "java(ActorPersistenceMapper.mapper.toActor(characterMovieDto.getActorDto()))")
    CharacterMovie toCharacterMovie(CharacterMovieDto characterMovieDto);

    List<CharacterMovie> toCharacterMovieList(List<CharacterMovieDto> characterMovieDtoList);

    @Mapping(target = "actorDto", expression = "java(ActorPersistenceMapper.mapper.toActorDto(characterMovie.getActor()))")
    CharacterMovieDto toCharacterMovieDto(CharacterMovie characterMovie);

    List<CharacterMovieDto> toCharacterMovieDtoList(List<CharacterMovie> characterMovieList);

}
