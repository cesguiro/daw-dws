package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.persistence.dao.db.DBUtil;
import org.springframework.stereotype.Component;

import java.util.List;


public interface MovieDao {

    List<Movie> findAll();

}
