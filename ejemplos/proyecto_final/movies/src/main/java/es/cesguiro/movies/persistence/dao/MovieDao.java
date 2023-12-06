package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.common.dto.MovieDto;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

@Component
public interface MovieDao {

    public Stream<MovieDto> getAll(Integer page, Integer pageSize);

    public Optional<MovieDto> find(int id);

    long count();
}
