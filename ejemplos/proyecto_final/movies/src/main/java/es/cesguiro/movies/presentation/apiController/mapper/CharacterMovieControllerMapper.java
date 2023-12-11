package es.cesguiro.movies.presentation.apiController.mapper;

import es.cesguiro.movies.common.dto.CharacterMovieDto;
import es.cesguiro.movies.presentation.apiController.request.CharacterMovieRequest;
import es.cesguiro.movies.presentation.apiController.response.CharacterMovieResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacterMovieControllerMapper {

    CharacterMovieControllerMapper mapper = Mappers.getMapper(CharacterMovieControllerMapper.class);

    @Mapping(target = "actorResponse", expression = "java(ActorControllerMapper.mapper.toActorResponse(characterMovieDto.getActorDto()))")
    CharacterMovieResponse toCharacterMovieResponse(CharacterMovieDto characterMovieDto);

    List<CharacterMovieResponse> toCharacterMovieResponseList(List<CharacterMovieDto> characterMovieDtoList);

    @Mapping(target = "actorDto", source = "actorRequest")
    CharacterMovieDto toCharacterMovieDto(CharacterMovieRequest characterMovieRequest);

    List<CharacterMovieDto> toCharacterMovieDtoList(List<CharacterMovieRequest> characterMovieRequest);
}
