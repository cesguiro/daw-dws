package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.domain.repository.MovieRepository;
import es.cesguiro.movies.persistence.mapper.MovieRowMapper;
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
    public List<Movie> getAll(Integer page) {
        String sql = String.format("SELECT * FROM movies LIMIT %d, %d", (page - 1) * LIMIT, LIMIT);
        /*if(page.isPresent()) {
            int offset = (page.get()-1) * LIMIT;
            sql += String.format(" LIMIT %d, %d", offset, LIMIT);
        }*/
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
            DBUtil.close(connection);
            Optional<Movie> result = (resultSet.next())? Optional.of(new MovieRowMapper().mapRow(resultSet, 1)): Optional.empty();
            /*resultSet.next()) {

            } else {
                return null;
            }*/
            return result;
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
