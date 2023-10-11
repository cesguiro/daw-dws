package es.cesguiro.movies.persistence.mapper.impl;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.persistence.entity.impl.MovieEntity;
import es.cesguiro.movies.persistence.mapper.GenericMapper;
import org.mapstruct.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieMapper implements GenericMapper<MovieEntity> {

    @Override
    public MovieEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new MovieEntity(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getInt("year"),
                resultSet.getInt("runtime")
        );
    }
}
