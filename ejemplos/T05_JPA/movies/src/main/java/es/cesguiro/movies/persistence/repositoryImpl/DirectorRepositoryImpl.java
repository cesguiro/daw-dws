package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.repository.DirectorRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Override
    public int insert(Director director) {
        return 0;
    }

    @Override
    public Optional<Director> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Director director) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<Director> findByMovieId(int movieId) {
        return Optional.empty();
    }
}
