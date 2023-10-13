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
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        String sql = "SELECT * FROM movies";
        if(page != null) {
            int offset = (page - 1) * pageSize;
            sql += String.format(" LIMIT %d, %d", offset, pageSize);
        }
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, sql, null);
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
            return movies;
        }  catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<Movie> find(int id) {
        final String SQL = "SELECT * FROM movies WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            if (resultSet.next()) {
                return Optional.of(
                        new Movie(
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

    @Override
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
}
