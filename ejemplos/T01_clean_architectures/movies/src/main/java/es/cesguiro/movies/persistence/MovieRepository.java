package es.cesguiro.movies.persistence;

import es.cesguiro.movies.business.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MovieRepository {
    List<Movie> getAll();

    Movie find(String id);
}
