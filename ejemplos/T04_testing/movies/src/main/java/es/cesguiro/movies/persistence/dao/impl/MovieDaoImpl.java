package es.cesguiro.movies.persistence.dao.impl;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.persistence.dao.MovieDao;
import es.cesguiro.movies.persistence.dao.db.DBUtil;
import es.cesguiro.movies.persistence.dao.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

@Component
public class MovieDaoImpl implements MovieDao {

    private final DBUtil dbUtil;
    private MovieMapper movieMapper;
    private final String tableName = "movies";
    private final String primaryKey = "id";


    @Autowired
    public MovieDaoImpl(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
        this.movieMapper = new MovieMapper();
    }

    public List<Movie> findAll() {
        final String SQL = "SELECT * FROM " + this.tableName;
        ResultSet resultSet = dbUtil.select(SQL, null);
        return movieMapper.toMovieList(resultSet);
    }


}
