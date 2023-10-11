package es.cesguiro.movies.persistence.mapper.impl;

import es.cesguiro.movies.persistence.entity.impl.DirectorEntity;
import es.cesguiro.movies.persistence.mapper.GenericMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectorMapper implements GenericMapper<DirectorEntity> {
    @Override
    public DirectorEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new DirectorEntity(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("birthYear"),
                resultSet.getInt("deathYear")
        );
    }
}
