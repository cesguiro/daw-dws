package es.cesguiro.movies.persistence.impl;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.db.exception.DBConnectionException;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.persistence.ActorRepository;
import es.cesguiro.movies.persistence.exception.SQLStatmentException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActorRepositoryImpl implements ActorRepository {

    @Override
    public void insert(Actor actor) {
        final String SQL = "INSERT INTO actors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(actor.getName());
        params.add(actor.getBirthYear());
        params.add(actor.getDeathYear());
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
