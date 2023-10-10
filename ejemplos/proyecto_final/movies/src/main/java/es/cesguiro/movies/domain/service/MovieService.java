package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.controller.DTO.movie.MovieDetailDTO;
import es.cesguiro.movies.controller.DTO.movie.MovieListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<MovieListDTO> getAll(Integer page, Integer pageSize);

    MovieDetailDTO find(int id);

    int getTotalNumberOfRecords();
}
