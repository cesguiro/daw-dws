package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.repository.MovieRepository;
import es.cesguiro.movies.mapper.MovieMapper;
import es.cesguiro.movies.persistence.dao.MovieDAO;
import es.cesguiro.movies.persistence.model.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;
import java.sql.Connection;
import java.sql.SQLException;
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
        //return movieDAO.findAll().stream().map(MovieMapper.mapper::toMovie).toList();
        return MovieMapper.mapper.toMovieList(movieDAO.findAll());
    }

    @Override
    public Optional<Movie> find(int id) {
            //MovieEntity movieEntity = movieDAO.findById(id).get();
            /*if(movieEntity != null) {
                movieEntity.getDirectorEntity();
                System.out.println(movieEntity.getDirectorEntity().getName());
                System.out.println(movieEntity.getDirectorEntity().getBirthYear());
                movieEntity.getActorEntities();
                System.out.println(movieEntity.getActorEntities().get(1).getName());
                //movieEntity.getCharacterEntities(connection, characterMovieDAO).forEach(CharacterMovieEntity -> CharacterMovieEntity.getActorEntity(connection, actorDAO));
                //movieEntity.getCharacterEntities().forEach(CharacterEntity -> CharacterEntity.getActor(connection, actorDAO));
            }*/
            return Optional.ofNullable(MovieMapper.mapper.toMovieWithDirectorAndActors(movieDAO.findById(id).get()));
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
