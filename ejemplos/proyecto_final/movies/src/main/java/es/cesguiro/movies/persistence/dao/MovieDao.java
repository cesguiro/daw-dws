package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.common.dto.MovieDto;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

@Component
public interface MovieDao {

    Stream<MovieDto> getAll(Integer page, Integer pageSize);

    Optional<MovieDto> find(int id);

    long count();

    MovieDto save(MovieDto movieDto);

    void delete(MovieDto movieDto);

    Stream<MovieDto> findByDirectorId(int directorId);
}
