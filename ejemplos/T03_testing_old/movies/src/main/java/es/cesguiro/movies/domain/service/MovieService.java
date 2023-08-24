package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.domain.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<Movie> getAll();

    Movie find(int id);
}
