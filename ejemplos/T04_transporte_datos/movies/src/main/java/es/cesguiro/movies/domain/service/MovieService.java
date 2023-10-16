package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.dto.movie.MovieDetailDTO;
import es.cesguiro.movies.dto.movie.MovieListDTO;
import es.cesguiro.movies.domain.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<MovieListDTO> getAll(Integer page, Integer pageSize);
    List<MovieListDTO> getAll();

    MovieDetailDTO find(int id);

    int getTotalNumberOfRecords();
}
