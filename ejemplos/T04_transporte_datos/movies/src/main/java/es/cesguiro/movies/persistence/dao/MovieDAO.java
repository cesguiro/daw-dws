package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.mapper.MovieMapper;
import es.cesguiro.movies.persistence.model.MovieEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDAO {

    public List<MovieEntity> findAll(Integer page, Integer pageSize) {
        String sql = "SELECT * FROM movies";
        if(page != null) {
            int offset = (page - 1) * pageSize;
            sql += String.format(" LIMIT %d, %d", offset, pageSize);
        }
        List<MovieEntity> movieEntities = new ArrayList<>();
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, sql, null);
            while (resultSet.next()) {
                movieEntities.add(MovieMapper.mapper.toMovieEntity(resultSet));
            }
            return movieEntities;
        }  catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
