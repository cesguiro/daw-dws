package es.cesguiro.movies.persistence.impl;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.db.exception.DBConnectionException;
import es.cesguiro.movies.persistence.MovieRepository;
import es.cesguiro.movies.http_errors.ResourceNotFoundException;
import es.cesguiro.movies.persistence.exception.SQLStatmentException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private final int LIMIT = 10;

    @Override
    public List<Movie> getAll(Optional<Integer> page) {
        String sql = "SELECT * FROM movies";
        if(page.isPresent()) {
            int offset = (page.get()-1) * LIMIT;
            sql += String.format(" LIMIT %d, %d", offset, LIMIT);
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
            DBUtil.close(connection);
            return movies;
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + sql);
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
                throw new ResourceNotFoundException("Id movie: " + id);
            }
        }catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
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
        }catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }
}
