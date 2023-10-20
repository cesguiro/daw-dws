package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.mapper.MovieMapper;
import es.cesguiro.movies.persistence.model.ActorEntity;
import es.cesguiro.movies.persistence.model.MovieEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieDAO {

    public List<MovieEntity> findAll(Integer page, Integer pageSize) {
        String sql = "SELECT * FROM movies";
        if(page != null) {
            int offset = (page - 1) * pageSize;
            sql += String.format(" LIMIT %d, %d", offset, pageSize);
        }
        List<MovieEntity> movieEntities = new ArrayList<>();
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, sql, null);
            while (resultSet.next()) {
                movieEntities.add(MovieMapper.mapper.toMovieEntity(resultSet));
            }
            return movieEntities;
        }  catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Optional<MovieEntity> find(int id) {
        final String SQL = "SELECT * FROM movies WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            if (resultSet.next()) {
                return Optional.of(
                        new MovieEntity(
                                resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getInt("year"),
                                resultSet.getInt("runtime")
                        )
                );
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public int getTotalNumberOfRecords() {
        final String SQL = "SELECT COUNT(*) FROM movies";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            DBUtil.close(connection);
            resultSet.next();
            return (int) resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("SQL: " + SQL);
        }
    }

    public int insert(MovieEntity movieEntity) {
        final String SQL = "INSERT INTO movies (title, year, runtime, director_id) VALUES (?, ?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(movieEntity.getTitle());
        params.add(movieEntity.getYear());
        params.add(movieEntity.getRuntime());
        params.add(movieEntity.getDirectorId());
        Connection connection = DBUtil.open();
        int id = DBUtil.insert(connection, SQL, params);
        DBUtil.close(connection);
        return id;
    }

    public void addActor(int movieId, ActorEntity actorEntity) {
        final String SQL = "INSERT INTO actors_movies (actor_id, movie_id) VALUES (?, ?)";
        List<Object> params = new ArrayList<>();
        Connection connection = DBUtil.open();
        DBUtil.insert(connection, SQL, List.of(actorEntity.getId(), movieId));
        DBUtil.close(connection);
    }
}
