package es.cesguiro.movies.domain.repository;

import es.cesguiro.movies.domain.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

@Component
public interface MovieRepository {

    Stream<Movie> getAll(Integer page, Integer pageSize);

    Optional<Movie> find(int id);

    long getTotalNumberOfRecords();

    Movie save(Movie movie);

    Stream<Movie> findByDirectorId(int directorId);

    void delete(Movie movie);

}
