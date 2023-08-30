package es.cesguiro.movies.persistence.impl;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.db.exception.DBConnectionException;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.persistence.DirectorRepository;
import es.cesguiro.movies.persistence.exception.SQLStatmentException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
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
        try (Connection connection = DBUtil.open()){
            DBUtil.insert(connection, SQL, params);
            DBUtil.close(connection);
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
