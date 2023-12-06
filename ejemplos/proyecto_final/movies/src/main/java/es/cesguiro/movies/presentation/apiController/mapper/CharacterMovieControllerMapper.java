package es.cesguiro.movies.presentation.apiController.mapper;

import es.cesguiro.movies.presentation.apiController.response.CharacterMovieResponse;
import es.cesguiro.movies.common.dto.CharacterMovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CharacterMovieControllerMapper {

    CharacterMovieControllerMapper mapper = Mappers.getMapper(CharacterMovieControllerMapper.class);

    @Mapping(target = "actorResponse", expression = "java(ActorControllerMapper.mapper.toActorResponse(characterMovieDTO.getActorDTO()))")
    CharacterMovieResponse toCharacterMovieResponse(CharacterMovieDto characterMovieDTO);
}
