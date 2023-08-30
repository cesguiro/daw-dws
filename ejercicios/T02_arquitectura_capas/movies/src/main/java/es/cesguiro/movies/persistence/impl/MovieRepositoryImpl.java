package es.cesguiro.movies.persistence.impl;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.persistence.MovieRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Override
    public List<Movie> getAll() {
        final String SQL = "SELECT * FROM movies";
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            while (resultSet.next()) {
                movies.add(
                        new Movie(
                                resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getInt("year"),
                                resultSet.getInt("runtime")
                        )
                );
            }
            DBUtil.close(connection);
            return movies;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Movie find(int id) {
        final String SQL = "SELECT * FROM movies WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            DBUtil.close(connection);
            if(resultSet.next()) {
                return new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("year"),
                        resultSet.getInt("runtime")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
