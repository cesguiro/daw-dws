package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.repository.ActorRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ActorRepositoryImpl implements ActorRepository {

    @Override
    public int insert(Actor actor) {
        final String SQL = "INSERT INTO actors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(actor.getName());
        params.add(actor.getBirthYear());
        params.add(actor.getDeathYear());
        Connection connection = DBUtil.open(true);
        int id = DBUtil.insert(connection, SQL, params);
        DBUtil.close(connection);
        return id;
    }

    @Override
    public Optional<Actor> find(int id) {
        final String SQL = "SELECT * FROM actors WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open(true)){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            if (resultSet.next()) {
                return Optional.of(
                        new Actor(
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

    @Override
    public List<Actor> findByMovieId(int movieId) {
        List<Actor> actors = new ArrayList<>();
        final String SQL = """
                SELECT a.* FROM actors a
                INNER JOIN actors_movies am ON am.actor_id = a.id
                INNER JOIN movies m ON m.id = am.movie_id AND m.id = ?
            """;
        try (Connection connection = DBUtil.open(true)){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            DBUtil.close(connection);
            if(!resultSet.next()) {
                return null;
            }
            do {
                actors.add(
                        new Actor(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("birthYear"),
                                (resultSet.getObject("deathYear") != null)? resultSet.getInt("deathYear") : null
                        )
                );
            } while (resultSet.next());
            return actors;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(Actor actor) {
        final String SQL = "UPDATE actors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(actor.getName());
        params.add(actor.getBirthYear());
        params.add(actor.getDeathYear());
        params.add(actor.getId());
        Connection connection = DBUtil.open(true);
        DBUtil.update(connection, SQL, params);
        DBUtil.close(connection);
    }

    @Override
    public void delete(int id) {
        final String SQL = "DELETE FROM actors WHERE id = ?";
        Connection connection = DBUtil.open(true);
        DBUtil.delete(connection, SQL, List.of(id));
        DBUtil.close(connection);
    }

}
