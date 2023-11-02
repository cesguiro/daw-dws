package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.repository.MovieRepository;
import es.cesguiro.movies.mapper.MovieMapper;
import es.cesguiro.movies.persistence.dao.ActorDAO;
import es.cesguiro.movies.persistence.dao.CharacterMovieDAO;
import es.cesguiro.movies.persistence.dao.DirectorDAO;
import es.cesguiro.movies.persistence.dao.MovieDAO;
import es.cesguiro.movies.persistence.model.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Autowired
    MovieDAO movieDAO;

    @Autowired
    DirectorDAO directorDAO;

    @Autowired
    CharacterMovieDAO characterMovieDAO;

    @Autowired
    ActorDAO actorDAO;

    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        try(Connection connection = DBUtil.open(true)) {
            List<MovieEntity> movieEntities = movieDAO.findAll(connection, page, pageSize);
            List<Movie> movies = movieEntities.stream()
                    .map(movieEntity -> MovieMapper.mapper.toMovie(movieEntity))
                    .toList();
            return movies;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Movie> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            MovieEntity movieEntity = movieDAO.find(connection, id).get();
            if(movieEntity != null) {
                movieEntity.getDirectorEntity(connection, directorDAO);
                movieEntity.getCharacterEntities(connection, characterMovieDAO).forEach(CharacterMovieEntity -> CharacterMovieEntity.getActorEntity(connection, actorDAO));
                //movieEntity.getCharacterEntities().forEach(CharacterEntity -> CharacterEntity.getActor(connection, actorDAO));
            }
            /*DirectorEntity directorEntity = directorDAO.findByMovieId(connection, id).orElse(null);
            List<ActorEntity> actorEntities = actorDAO.findByMovieId(connection, id);
            Director director = DirectorMapper.mapper.toDirector(directorEntity);
            List<Actor> actors = actorEntities.stream()
                    .map(actorEntity -> ActorMapper.mapper.toActor(actorEntity))
                    .toList();
            Movie movie = MovieMapper.mapper.toMovie(movieEntity.get());
            if(movie != null) {
                movie.setDirector(director);
                movie.setActors(actors);
            }*/
            return Optional.ofNullable(MovieMapper.mapper.toMovie(movieEntity));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalNumberOfRecords() {
        try(Connection connection = DBUtil.open(true)) {
            return movieDAO.getTotalNumberOfRecords(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Movie movie) {
        try (Connection connection = DBUtil.open(false)){
            MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(movie);
            int id = movieDAO.insert(connection, movieEntity);
            return id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Movie movie) {
        try (Connection connection = DBUtil.open(false)){
            MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(movie);
            movieDAO.update(connection, movieEntity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
