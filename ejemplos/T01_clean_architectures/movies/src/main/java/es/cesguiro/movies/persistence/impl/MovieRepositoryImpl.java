package es.cesguiro.movies.persistence.impl;

import es.cesguiro.movies.business.entity.Movie;
import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.db.exception.DBConnectionException;
import es.cesguiro.movies.persistence.MovieRepository;
import es.cesguiro.movies.persistence.exception.ResourceNotFoundException;
import es.cesguiro.movies.persistence.exception.SQLStatmentException;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieRepositoryImpl implements MovieRepository {

    @Override
    public List<Movie> getAll() {
        final String SQL = "SELECT * FROM movies";
        List<Movie> movies = new ArrayList<>();
        try {
            Connection connection = DBUtil.open();
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            while (resultSet.next()) {
                movies.add(
                        new Movie(
                                resultSet.getString("imdb_id"),
                                resultSet.getString("title"),
                                resultSet.getInt("year"),
                                resultSet.getInt("runtime")
                        )
                );
            }
            DBUtil.close(connection);
            return movies;
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    @Override
    public Movie find(String id) {
        final String SQL = "SELECT * FROM movies WHERE imdb_id = ? LIMIT 1";
        try {
            Connection connection = DBUtil.open();
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            DBUtil.close(connection);
            if(resultSet.next()) {
                return new Movie(
                        resultSet.getString("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("year"),
                        resultSet.getInt("runtime")
                );
            } else {
                throw new ResourceNotFoundException("Id movie: " + id);
            }
        }catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }
}
