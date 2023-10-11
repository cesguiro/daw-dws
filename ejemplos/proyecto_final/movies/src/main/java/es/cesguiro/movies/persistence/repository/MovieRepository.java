package es.cesguiro.movies.persistence.repository;

import es.cesguiro.movies.persistence.entity.impl.MovieEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MovieRepository {

    Optional<List<MovieEntity>> getAll(Integer page, Integer pageSize);

    Optional<MovieEntity> find(int id);

    int getTotalNumberOfRecords();
}
