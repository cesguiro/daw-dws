package es.cesguiro.movies.domain.repository;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.dto.MovieDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MovieRepository {

    List<MovieDTO> getAll(Integer page, Integer pageSize);

    Optional<MovieDTO> find(int id);

    int getTotalNumberOfRecords();

    int insert(Movie movie);
}
