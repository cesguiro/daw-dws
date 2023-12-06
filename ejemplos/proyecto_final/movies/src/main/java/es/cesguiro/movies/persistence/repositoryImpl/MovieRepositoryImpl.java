package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.common.dto.MovieDto;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.repository.MovieRepository;
import es.cesguiro.movies.persistence.dao.MovieDao;
import es.cesguiro.movies.persistence.mapper.MoviePersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class MovieRepositoryImpl implements MovieRepository {

    @Qualifier("MovieDaoJpaImpl")
    private final MovieDao movieDao;
    //private final CharacterMovieJpaRepository characterMovieJpaRepository;

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
    public Movie insert(Movie movie) {
        /*MovieEntity movieEntity = movieJpaRepository.save(MovieMapper.mapper.toMovieEntity(movie));
        return MovieMapper.mapper.toMovieWithDirectorAndCharacterMovies(movieEntity);*/
        return null;
    }

    @Override
    @Transactional
    public Movie update(Movie movie) {
        /*MovieEntity movieEntity = movieJpaRepository.save(MovieMapper.mapper.toMovieEntity(movie));
        return MovieMapper.mapper.toMovieWithDirectorAndCharacterMovies(movieEntity);*/
        return null;
    }

    @Override
    public List<Movie> findByDirectorId(int directorId) {
        //return MovieMapper.mapper.toMovieList(movieJpaRepository.findByDirectorEntityId(directorId));
        return null;
    }

    @Override
    @Transactional
    public void delete(Movie movie) {
        //movieJpaRepository.delete(MovieMapper.mapper.toMovieEntity(movie));
    }

}
