package es.cesguiro.movies.persistence;

import es.cesguiro.movies.business.entity.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> getAll();

    Movie find(String id);
}
