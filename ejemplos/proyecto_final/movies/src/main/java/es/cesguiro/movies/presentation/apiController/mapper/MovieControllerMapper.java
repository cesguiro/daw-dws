package es.cesguiro.movies.presentation.apiController.mapper;

import es.cesguiro.movies.presentation.apiController.response.MovieResponse;
import es.cesguiro.movies.common.dto.MovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovieControllerMapper {

    MovieControllerMapper mapper = Mappers.getMapper(MovieControllerMapper.class);

    /*@Mapping(target = "directorResponse", expression = "java(DirectorMapper.mapper.toDirectorResponse(movieDto.getDirectorDto()))")
    @Mapping(target = "characterMovieResponseList", expression = "java(CharacterMovieMapper.mapper.toCharacterMovieResponseList(movieDto.getCharacterMovieDtoList()))")*/
    /*@Mapping(target = "directorResponse", ignore = true)
    @Mapping(target = "characterMovieResponseList", ignore = true)
    MovieResponse toMovieResponseWithDirectorAndCharacterMovies(MovieDto movieDto);*/

    @Mapping(target = "directorResponse", source = "directorDto")
    MovieResponse toMovieResponse(MovieDto movieDto);

}
