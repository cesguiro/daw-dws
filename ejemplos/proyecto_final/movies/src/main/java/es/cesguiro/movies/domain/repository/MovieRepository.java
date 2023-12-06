package es.cesguiro.movies.domain.repository;

import es.cesguiro.movies.domain.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public interface MovieRepository {

    Stream<Movie> getAll(Integer page, Integer pageSize);

    Optional<Movie> find(int id);

    long getTotalNumberOfRecords();

    Movie insert(Movie movie);

    Movie update(Movie movie);

    List<Movie> findByDirectorId(int directorId);

    void delete(Movie movie);

}
