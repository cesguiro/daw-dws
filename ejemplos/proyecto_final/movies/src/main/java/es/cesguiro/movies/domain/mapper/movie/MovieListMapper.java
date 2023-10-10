package es.cesguiro.movies.domain.mapper.movie;

import es.cesguiro.movies.controller.DTO.movie.MovieListDTO;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.persistence.entity.impl.MovieEntity;

import java.util.ArrayList;
import java.util.List;

public class MovieListMapper {


    private MovieListDTO toDTO(Movie movie) {
        return new MovieListDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getYear(),
                movie.getRuntime()
        );
    }

    private Movie fromEntity(MovieEntity movieEntity) {
        return new Movie(
                movieEntity.getId(),
                movieEntity.getTitle(),
                movieEntity.getYear(),
                movieEntity.getRuntime()
        );
    }

    public List<MovieListDTO> ToListDTO(List<Movie> movies) {
        List<MovieListDTO> movieListDTO = new ArrayList<>();
        for (Movie movie: movies) {
            movieListDTO.add(this.toDTO(movie));
        }
        return movieListDTO;
    }

    public List<Movie> fromListEntity(List<MovieEntity> movieEntities) {
        List<Movie> movies = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntities) {
            movies.add(this.fromEntity(movieEntity));
        }
        return movies;
    }

}
