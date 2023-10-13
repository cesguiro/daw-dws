package es.cesguiro.movies.persistence;

import es.cesguiro.movies.domain.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MovieRepository {

    List<Movie> getAll(Integer page, Integer pageSize);

    Optional<Movie> find(int id);

    int getTotalNumberOfRecords();
}
