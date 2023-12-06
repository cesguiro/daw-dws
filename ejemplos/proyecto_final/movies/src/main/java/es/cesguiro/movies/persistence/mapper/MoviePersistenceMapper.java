package es.cesguiro.movies.persistence.mapper;

import es.cesguiro.movies.common.dto.MovieDto;
import es.cesguiro.movies.domain.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface MoviePersistenceMapper {
    MoviePersistenceMapper mapper = Mappers.getMapper(MoviePersistenceMapper.class);

    Movie toMovie(MovieDto movieDto);

    @Mapping(target = "director", expression = "java(DirectorPersistenceMapper.mapper.toDirector(movieDto.getDirectorDto()))")
    Movie toMovieWithDirectorAndCharacters(MovieDto movieDto);
}
