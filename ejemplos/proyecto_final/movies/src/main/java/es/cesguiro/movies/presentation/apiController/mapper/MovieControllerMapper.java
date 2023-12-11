package es.cesguiro.movies.presentation.apiController.mapper;

import es.cesguiro.movies.common.dto.MovieDto;
import es.cesguiro.movies.presentation.apiController.request.MovieRequest;
import es.cesguiro.movies.presentation.apiController.response.MovieResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovieControllerMapper {

    MovieControllerMapper mapper = Mappers.getMapper(MovieControllerMapper.class);

    @Mapping(target = "directorResponse", source = "directorDto")
    @Mapping(target = "characterMovieResponseList", expression = "java(CharacterMovieControllerMapper.mapper.toCharacterMovieResponseList(movieDto.getCharacterMovieDtoList()))")
    MovieResponse toMovieResponse(MovieDto movieDto);

    @Mapping(target = "directorDto", source = "directorRequest")
    @Mapping(target = "characterMovieDtoList", expression = "java(CharacterMovieControllerMapper.mapper.toCharacterMovieDtoList(movieRequest.getCharacterMovieRequestList()))")
    MovieDto toMovieDto(MovieRequest movieRequest);

}