package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.domain.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MovieService {
    List<Movie> getAll(Optional<Integer> page);

    Movie find(int id);

    int getTotalNumberOfRecords();
}
