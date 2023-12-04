package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MovieService {
    List<Movie> getAll(Integer page, Integer pageSize);
    List<Movie> getAll();

    Movie find(int id);

    long getTotalNumberOfRecords();

    Movie create(Movie movie);

    Movie update(Movie movie);

    List<Movie> findByDirectorId(int directorId);

    void delete(int id);
}
