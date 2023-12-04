package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.domain.entity.CharacterMovie;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.repository.MovieRepository;
import es.cesguiro.movies.mapper.MovieMapper;
import es.cesguiro.movies.persistence.dao.CharacterMovieDAO;
import es.cesguiro.movies.persistence.dao.MovieDAO;
import es.cesguiro.movies.persistence.model.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class MovieRepositoryImpl implements MovieRepository {

    private final MovieDAO movieDAO;
    private final CharacterMovieDAO characterMovieDAO;

    @Autowired
    public MovieRepositoryImpl(MovieDAO movieDAO, CharacterMovieDAO characterMovieDAO) {
        this.movieDAO = movieDAO;
        this.characterMovieDAO = characterMovieDAO;
    }
    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        List<MovieEntity> movieEntities;
        if(page != null && page > 0) {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            movieEntities = movieDAO.findAll(pageable).stream().toList();
        } else {
            movieEntities = movieDAO.findAll();
        }
        return MovieMapper.mapper.toMovieList(movieEntities);
    }

    @Override
    public Optional<Movie> find(int id) {

        MovieEntity movieEntity = movieDAO.findById(id).orElse(null);
        if(movieEntity == null) {
            return Optional.empty();
        }
        return Optional.of(MovieMapper.mapper.toMovieWithDirectorAndCharacterMovies(movieEntity));
    }

    @Override
    public long getTotalNumberOfRecords() {
        return movieDAO.count();
    }

    @Override
    @Transactional
    public Movie insert(Movie movie) {
        MovieEntity movieEntity = movieDAO.save(MovieMapper.mapper.toMovieEntity(movie));
        return MovieMapper.mapper.toMovieWithDirectorAndCharacterMovies(movieEntity);
    }

    @Override
    @Transactional
    public Movie update(Movie movie) {
        MovieEntity movieEntity = movieDAO.save(MovieMapper.mapper.toMovieEntity(movie));
        return MovieMapper.mapper.toMovieWithDirectorAndCharacterMovies(movieEntity);
    }

    @Override
    public List<Movie> findByDirectorId(int directorId) {
        return MovieMapper.mapper.toMovieList(movieDAO.findByDirectorEntityId(directorId));
    }

    @Override
    @Transactional
    public void delete(Movie movie) {
        movieDAO.delete(MovieMapper.mapper.toMovieEntity(movie));
    }

}
