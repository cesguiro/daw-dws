package es.cesguiro.movies.domain.mapper;

import es.cesguiro.movies.controller.DTO.movie.MovieDetailDTO;
import es.cesguiro.movies.controller.DTO.movie.MovieListDTO;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.persistence.entity.impl.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    Movie toModel(MovieEntity entity);
    MovieDetailDTO toDetailDTO(Movie movie);
    MovieListDTO toListDTO(Movie movie);
}
