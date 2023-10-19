package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.dto.movie.MovieDetailDTO;
import es.cesguiro.movies.dto.movie.MovieListDTO;
import es.cesguiro.movies.domain.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<Movie> getAll(Integer page, Integer pageSize);
    List<Movie> getAll();

    Movie find(int id);

    int getTotalNumberOfRecords();

    int create(Movie movie, int directorId, List<Integer> actorIds);
}
