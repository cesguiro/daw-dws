package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.repository.MovieRepository;
import es.cesguiro.movies.mapper.MovieMapper;
import es.cesguiro.movies.persistence.dao.MovieDAO;
import es.cesguiro.movies.persistence.model.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovieRepositoryImpl implements MovieRepository {

    private final MovieDAO movieDAO;

    @Autowired
    public MovieRepositoryImpl(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
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
        /*MovieEntity movieEntity = movieDAO.findById(id).orElse(null);
        if(movieEntity != null) {
            //movieEntity.getDirectorEntity();
            //movieEntity.getDirectorEntity().getName();
        }*/
        //return Optional.ofNullable(MovieMapper.mapper.toMovie(movieEntity));
        MovieEntity movieEntity = movieDAO.findById(id).orElse(null);
        if(movieEntity == null) {
            return Optional.empty();
        }
        //return Optional.ofNullable(MovieMapper.mapper.toMovie(movieEntity));
        return Optional.of(MovieMapper.mapper.toMovieWithDirectorAndCharacterMovies(movieEntity));
    }

    @Override
    public long getTotalNumberOfRecords() {
        return movieDAO.count();
    }

    @Override
    public int insert(Movie movie) {
        return 0;
    }

    @Override
    public void update(Movie movie) {

    }
}
