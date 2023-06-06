package es.cesguiro.movies.business.service;

import es.cesguiro.movies.business.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<Movie> getAll();

    Movie find(String id);
}
