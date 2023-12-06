package es.cesguiro.movies.domain.service;

import es.cesguiro.movies.common.dto.CharacterMovieDto;
import es.cesguiro.movies.common.dto.MovieDto;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public interface MovieService {
    Stream<MovieDto> getAll(Integer page, Integer pageSize);
    Stream<MovieDto> getAll();

    MovieDto find(int movieId);

    long getTotalNumberOfRecords();

    MovieDto create(MovieDto movieDTO);

    MovieDto update(MovieDto movieDTO);

    Stream<MovieDto> findByDirectorId(int directorId);

    void delete(int movieId);

    MovieDto addCharacterMovie(int movieId, CharacterMovieDto characterMovieDTO);

    MovieDto updateCharacterMovie(int movieId, CharacterMovieDto characterMovieDTO);

    void deleteCharacterMovie(int movieId, int characterMovieId);
}
