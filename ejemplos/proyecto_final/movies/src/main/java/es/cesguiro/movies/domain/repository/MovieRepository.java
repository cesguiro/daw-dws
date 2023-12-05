package es.cesguiro.movies.domain.repository;

import es.cesguiro.movies.controller.model.movie.MovieUpdateWeb;
import es.cesguiro.movies.domain.entity.CharacterMovie;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.dto.MovieDTO;
import es.cesguiro.movies.persistence.model.MovieEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public interface MovieRepository {

    Stream<MovieDTO> getAll(Integer page, Integer pageSize);

    Optional<MovieDTO> find(int id);

    long getTotalNumberOfRecords();

    Movie insert(Movie movie);

    Movie update(Movie movie);

    List<Movie> findByDirectorId(int directorId);

    void delete(Movie movie);

}
