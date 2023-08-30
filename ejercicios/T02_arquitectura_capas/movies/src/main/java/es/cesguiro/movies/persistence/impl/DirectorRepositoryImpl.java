package es.cesguiro.movies.persistence.impl;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.persistence.DirectorRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Override
    public void insert(Director director) {
        final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(director.getName());
        params.add(director.getBirthYear());
        params.add(director.getDeathYear());
        Connection connection = DBUtil.open();
        DBUtil.insert(connection, SQL, params);
        DBUtil.close(connection);
    }
}
