package es.cesguiro.movies.persistence;

import es.cesguiro.movies.dto.movie.MovieDetailDTO;
import es.cesguiro.movies.dto.movie.MovieListDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MovieRepository {

    List<MovieListDTO> getAll(Integer page, Integer pageSize);

    Optional<MovieDetailDTO> find(int id);

    int getTotalNumberOfRecords();
}
