package es.cesguiro.movies.persistence.DAO.impl;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.persistence.DAO.ActorDAO;
import es.cesguiro.movies.persistence.entity.impl.ActorEntity;
import es.cesguiro.movies.persistence.factory.impl.ActorFactory;
import es.cesguiro.movies.persistence.mapper.impl.ActorMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ActorDAOJDBCImpl extends GenericDAOJDBCImpl<ActorEntity, Integer> implements ActorDAO {

    public ActorDAOJDBCImpl() {
        super(new ActorMapper(), new ActorFactory());
    }
    @Override
    public Optional<List<ActorEntity>> findByMovieId(int movieId) {
        List<ActorEntity> actors = new ArrayList<>();
        final String SQL = """
                SELECT a.* FROM actors a
                INNER JOIN actors_movies am ON am.actor_id = a.id
                INNER JOIN movies m ON m.id = am.movie_id
                WHERE m.id = ?
            """;
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            DBUtil.close(connection);
            if(!resultSet.next()) {
                return Optional.empty();
            }
            do {
                 actors.add(mapper.mapRow(resultSet, 1));
            } while (resultSet.next());
            return Optional.of(actors);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
