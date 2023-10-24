package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.dto.MovieDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<MovieDTO> getAll(Integer page, Integer pageSize);
    List<MovieDTO> getAll();

    MovieDTO find(int id);

    int getTotalNumberOfRecords();

    int create(Movie movie, int directorId, List<Integer> actorIds);
}
