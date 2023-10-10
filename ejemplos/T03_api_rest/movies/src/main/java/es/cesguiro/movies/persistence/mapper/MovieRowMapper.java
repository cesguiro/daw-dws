package es.cesguiro.movies.persistence.mapper;

import es.cesguiro.movies.domain.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper {

    @Override
    public Movie mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Movie(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getInt("year"),
                resultSet.getInt("runtime")
        );
    }
}
