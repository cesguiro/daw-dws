package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.domain.entity.CharacterMovie;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.repository.DirectorRepository;
import es.cesguiro.movies.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public interface MovieService {
    Stream<MovieDTO> getAll(Integer page, Integer pageSize);
    Stream<MovieDTO> getAll();

    MovieDTO find(int id);

    long getTotalNumberOfRecords();

    Movie create(Movie movie);

    Movie update(Movie movie);

    List<Movie> findByDirectorId(int directorId);

    void delete(int id);

    Movie addCharacterMovie(int id, CharacterMovie characterMovie);

    Movie updateCharacterMovie(int id, CharacterMovie characterMovie);
}
