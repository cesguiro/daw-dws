package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.mapper.ActorMapper;
import es.cesguiro.movies.mapper.DirectorMapper;
import es.cesguiro.movies.persistence.model.ActorEntity;
import es.cesguiro.movies.persistence.model.DirectorEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ActorDAO {


    public List<ActorEntity> findByMovieId(Connection connection, int movieId) {
        List<ActorEntity> actorEntities = new ArrayList<>();
        final String SQL = """
                SELECT a.* FROM actors a
                INNER JOIN actors_movies am ON am.actor_id = a.id
                INNER JOIN movies m ON m.id = am.movie_id AND m.id = ?
            """;
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            if(!resultSet.next()) {
                return null;
            }
            do {
                actorEntities.add(ActorMapper.mapper.toActorEntity(resultSet));
            } while (resultSet.next());
            return actorEntities;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
