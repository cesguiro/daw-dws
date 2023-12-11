package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.repository.MovieRepository;
import es.cesguiro.movies.persistence.dao.MovieDao;
import es.cesguiro.movies.persistence.mapper.MoviePersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Qualifier("MovieDaoJpaImpl")
    private final MovieDao movieDao;

    @Autowired
    public MovieRepositoryImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }
    @Override
    public Stream<Movie> getAll(Integer page, Integer pageSize) {
        return movieDao
                .getAll(page, pageSize)
                .map(movieDto -> MoviePersistenceMapper.mapper.toMovie(movieDto));
    }

    @Override
    public Optional<Movie> find(int id) {
        return Optional.ofNullable(
                MoviePersistenceMapper
                        .mapper
                        .toMovieWithDirectorAndCharacters(
                                movieDao
                                        .find(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public long getTotalNumberOfRecords() {
        return movieDao.count();
    }

    @Override
    @Transactional
    public Movie save(Movie movie) {
        return MoviePersistenceMapper
                .mapper
                .toMovieWithDirectorAndCharacters(
                        movieDao
                                .save(
                                        MoviePersistenceMapper
                                                .mapper
                                                .toMovieDto(movie)
                                )
                );
    }

    @Override
    public Stream<Movie> findByDirectorId(int directorId) {
        return movieDao
                .findByDirectorId(directorId)
                .map(movieDto -> MoviePersistenceMapper.mapper.toMovie(movieDto));
    }

    @Override
    @Transactional
    public void delete(Movie movie) {
        movieDao.delete(
                MoviePersistenceMapper
                        .mapper
                        .toMovieDto(movie)
        );
    }

}
