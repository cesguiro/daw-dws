package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.mapper.ActorMapper;
import es.cesguiro.movies.mapper.CharacterMapper;
import es.cesguiro.movies.persistence.model.CharacterEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterDAO {

    public List<CharacterEntity> findByMovieId(Connection connection, int movieId) {
        List<CharacterEntity> characterEntities = new ArrayList<>();
        final String SQL = """
                SELECT c.* FROM actors_movies c
                INNER JOIN movies m ON m.id = c.movie_id AND m.id = ?
            """;
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            if(!resultSet.next()) {
                return null;
            }
            do {
                characterEntities.add(CharacterMapper.mapper.toCharacterEntity(resultSet));
            } while (resultSet.next());
            return characterEntities;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
