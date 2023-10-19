package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.persistence.model.ActorEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActorDAO {

    public int insert(ActorEntity actorEntity) {
        final String SQL = "INSERT INTO actors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(actorEntity.getName());
        params.add(actorEntity.getBirthYear());
        params.add(actorEntity.getDeathYear());
        Connection connection = DBUtil.open();
        int id = DBUtil.insert(connection, SQL, params);
        DBUtil.close(connection);
        return id;
    }

    public Optional<ActorEntity> find(int id) {
        final String SQL = "SELECT * FROM actors WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            if (resultSet.next()) {
                return Optional.of(
                        new ActorEntity(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("birthYear"),
                                (resultSet.getObject("deathYear") != null)? resultSet.getInt("deathYear") : null
                        )
                );
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void update(ActorEntity actorEntity) {
        final String SQL = "UPDATE actors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(actorEntity.getName());
        params.add(actorEntity.getBirthYear());
        params.add(actorEntity.getDeathYear());
        params.add(actorEntity.getId());
        Connection connection = DBUtil.open();
        DBUtil.update(connection, SQL, params);
        DBUtil.close(connection);
    }

    public void delete(int id) {
        final String SQL = "DELETE FROM actors WHERE id = ?";
        Connection connection = DBUtil.open();
        DBUtil.delete(connection, SQL, List.of(id));
        DBUtil.close(connection);
    }

    public Optional<ActorEntity> findByMovieId(int movieId) {
        final String SQL = """
            SELECT a.* FROM actors a
                INNER JOIN actors_movies am ON am.actor_id = a.id
                INNER JOIN movies m ON m.id = am.movie_id AND m.id = ?
        """;
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            if (resultSet.next()) {
                return Optional.of(
                        new ActorEntity(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("birthYear"),
                                (resultSet.getObject("deathYear") != null)? resultSet.getInt("deathYear") : null
                        )
                );
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
