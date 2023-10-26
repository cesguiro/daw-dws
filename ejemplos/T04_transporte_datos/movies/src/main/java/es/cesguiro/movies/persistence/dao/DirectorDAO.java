package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.mapper.DirectorMapper;
import es.cesguiro.movies.mapper.MovieMapper;
import es.cesguiro.movies.persistence.model.DirectorEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DirectorDAO {

    public int insert(Connection connection, DirectorEntity directorEntity) {
        final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(directorEntity.getName());
        params.add(directorEntity.getBirthYear());
        params.add(directorEntity.getDeathYear());
        int id = DBUtil.insert(connection, SQL, params);
        return id;
    }

    public Optional<DirectorEntity> find(Connection connection, int id) {
        final String SQL = "SELECT * FROM directors WHERE id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return Optional.of(resultSet.next()? DirectorMapper.mapper.toDirectorEntity(resultSet):null);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void update(Connection connection, DirectorEntity directorEntity) {
        final String SQL = "UPDATE directors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(directorEntity.getName());
        params.add(directorEntity.getBirthYear());
        params.add(directorEntity.getDeathYear());
        params.add(directorEntity.getId());
        DBUtil.update(connection, SQL, params);
        DBUtil.close(connection);
    }

    public void delete(Connection connection, int id) {
        final String SQL = "DELETE FROM directors WHERE id = ?";
        DBUtil.delete(connection, SQL, List.of(id));
        DBUtil.close(connection);
    }

    public Optional<DirectorEntity> findByMovieId(Connection connection, int movieId) {
        final String SQL = """
            SELECT d.* FROM directors d 
            INNER JOIN  movies m ON m.director_id = d.id
            WHERE m.id = ?
            LIMIT 1
        """;
        try{
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            return Optional.of(resultSet.next()? DirectorMapper.mapper.toDirectorEntity(resultSet):null);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
