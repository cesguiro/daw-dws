package es.cesguiro.movies.persistence.impl;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.persistence.dao.MovieDao;
import es.cesguiro.movies.persistence.dao.db.DBUtil;
import es.cesguiro.movies.persistence.dao.db.exception.DBConnectionException;
import es.cesguiro.movies.persistence.MovieRepository;
import es.cesguiro.movies.http_errors.ResourceNotFoundException;
import es.cesguiro.movies.persistence.dao.impl.MovieDaoImpl;
import es.cesguiro.movies.persistence.exception.SQLStatmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Value("${page.size}")
    private int LIMIT;

    private final MovieDao movieDao;

    @Autowired
    public MovieRepositoryImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }


    @Override
    public List<Movie> getAll(Optional<Integer> page, Optional<Integer> page_size) {
        /*String sql = "SELECT * FROM movies";
        if(page.isPresent()) {
            int limit = (page_size.isPresent())? page_size.get(): LIMIT;
            int offset = (page.get()-1) * limit;
            sql += String.format(" LIMIT %d, %d", offset, limit);
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
        }*/
        return movieDao.findAll();
    }

    @Override
    public Movie find(int id) {
        /*final String SQL = "SELECT * FROM movies WHERE id = ? LIMIT 1";
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
        }*/
        return null;
    }

    @Override
    public int getTotalNumberOfRecords() {
        /*final String SQL = "SELECT COUNT(*) FROM movies";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            DBUtil.close(connection);
            resultSet.next();
            return (int) resultSet.getInt(1);
        }catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }*/
        return 0;
    }
}
