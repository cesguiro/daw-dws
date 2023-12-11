package es.cesguiro.movies.persistence.mapper;

import es.cesguiro.movies.common.dto.MovieDto;
import es.cesguiro.movies.domain.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MoviePersistenceMapper {
    MoviePersistenceMapper mapper = Mappers.getMapper(MoviePersistenceMapper.class);

    Movie toMovie(MovieDto movieDto);

    @Mapping(target = "director", expression = "java(DirectorPersistenceMapper.mapper.toDirector(movieDto.getDirectorDto()))")
    @Mapping(target = "characterMovieList", expression = "java(CharacterMoviePersistenceMapper.mapper.toCharacterMovieList(movieDto.getCharacterMovieDtoList()))")
    Movie toMovieWithDirectorAndCharacters(MovieDto movieDto);

    @Mapping(target = "directorDto", expression = "java(DirectorPersistenceMapper.mapper.toDirectorDto(movie.getDirector()))")
    @Mapping(target = "characterMovieDtoList", expression = "java(CharacterMoviePersistenceMapper.mapper.toCharacterMovieDtoList(movie.getCharacterMovieList()))")
    MovieDto toMovieDto(Movie movie);
}
