package es.cesguiro.movies.domain.mapper.movie;

import es.cesguiro.movies.controller.DTO.movie.MovieDetailDTO;
import es.cesguiro.movies.controller.DTO.movie.MovieListDTO;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.persistence.entity.impl.MovieEntity;

public class MovieDetailMapper {

    public MovieDetailDTO toDTO(Movie movie) {
        return new MovieDetailDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getYear(),
                movie.getRuntime()
        );
    }

    public Movie fromEntity(MovieEntity movieEntity) {
        return new Movie(
                movieEntity.getId(),
                movieEntity.getTitle(),
                movieEntity.getYear(),
                movieEntity.getRuntime()
        );
    }
}
