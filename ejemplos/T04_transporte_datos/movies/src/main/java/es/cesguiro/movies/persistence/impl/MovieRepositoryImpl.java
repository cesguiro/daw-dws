package es.cesguiro.movies.persistence.impl;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.dto.mapper.MovieMapper;
import es.cesguiro.movies.dto.movie.MovieDetailDTO;
import es.cesguiro.movies.dto.movie.MovieListDTO;
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
    public List<MovieListDTO> getAll(Integer page, Integer pageSize) {
        String sql = "SELECT * FROM movies";
        if(page != null) {
            int offset = (page - 1) * pageSize;
            sql += String.format(" LIMIT %d, %d", offset, pageSize);
        }
        List<MovieListDTO> movieListDTOs = new ArrayList<>();
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, sql, null);
            while (resultSet.next()) {
                movieListDTOs.add(MovieMapper.mapper.toListDTO(resultSet));
                /*movieListDTOs.add(
                        new MovieListDTO(
                                resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getInt("year"),
                                null
                        )
                );*/
            }
            return movieListDTOs;
        }  catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<MovieDetailDTO> find(int id) {
        final String SQL = "SELECT * FROM movies WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            if (resultSet.next()) {
                /*return Optional.of(
                        new MovieDetailDTO(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getInt("year"),
                            resultSet.getInt("runtime")
                        )
                );*/
                return Optional.of(MovieMapper.mapper.toDetailDTO(resultSet));
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
