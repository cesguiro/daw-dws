package es.cesguiro.movies.persistence.impl;

import es.cesguiro.movies.business.entity.Movie;
import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.db.exception.DBConnectionException;
import es.cesguiro.movies.persistence.MovieRepository;
import es.cesguiro.movies.persistence.exception.SQLStatmentException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
