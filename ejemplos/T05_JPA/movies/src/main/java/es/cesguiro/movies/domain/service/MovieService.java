package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.controller.model.movie.MovieListWeb;
import es.cesguiro.movies.domain.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MovieService {
    List<Movie> getAll(Integer page, Integer pageSize);
    List<Movie> getAll();

    Movie find(int id);

    long getTotalNumberOfRecords();

    int create(Movie movie);

    void update(Movie movie, int directorId, List<Integer> actorIds);

    List<Movie> findByDirectorId(int directorId);
}
