package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.repository.MovieRepository;
import es.cesguiro.movies.mapper.ActorMapper;
import es.cesguiro.movies.mapper.DirectorMapper;
import es.cesguiro.movies.mapper.MovieMapper;
import es.cesguiro.movies.persistence.dao.ActorDAO;
import es.cesguiro.movies.persistence.dao.MovieDAO;
import es.cesguiro.movies.persistence.model.ActorEntity;
import es.cesguiro.movies.persistence.model.DirectorEntity;
import es.cesguiro.movies.persistence.model.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Autowired
    MovieDAO movieDAO;

    @Autowired
    ActorDAO actorDAO;

    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        List<MovieEntity> movieEntities = movieDAO.findAll(page, pageSize);
        List<Movie> movies = movieEntities.stream()
                .map(movieEntity -> MovieMapper.mapper.toMovie(movieEntity))
                .toList();
        return movies;
    }

    @Override
    public Optional<Movie> find(int id) {
        Optional<MovieEntity> movieEntity = movieDAO.find(id);
        if(movieEntity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(MovieMapper.mapper.toMovie(movieEntity.get()));
    }

    @Override
    public int getTotalNumberOfRecords() {
       return movieDAO.getTotalNumberOfRecords();
    }

    @Override
    public int insert(Movie movie) {
        MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(movie);
        int id = movieDAO.insert(movieEntity);
        //movie.getActors().forEach(actor -> movieDAO.addActor(0, ActorMapper.mapper.toEntity(actor)));
        movie.getActors().forEach(actor -> movieDAO.addActor(id, ActorMapper.mapper.toEntity(actor)));
        return id;
    }


}
