package es.cesguiro.movies.domain.repository;

import es.cesguiro.movies.controller.model.movie.MovieUpdateWeb;
import es.cesguiro.movies.domain.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MovieRepository {

    List<Movie> getAll(Integer page, Integer pageSize);

    Optional<Movie> find(int id);

    int getTotalNumberOfRecords();

    int insert(Movie movie);

    void update(Movie movie);
}
