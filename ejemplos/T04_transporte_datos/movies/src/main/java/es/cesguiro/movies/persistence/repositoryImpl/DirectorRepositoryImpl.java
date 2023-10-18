package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.repository.DirectorRepository;
import es.cesguiro.movies.mapper.DirectorMapper;
import es.cesguiro.movies.persistence.dao.DirectorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Autowired
    DirectorDAO directorDAO;

    @Override
    public int insert(Director director) {
        return directorDAO.insert(DirectorMapper.mapper.toDirectorEntity(director));
    }

    @Override
    public Optional<Director> find(int id) {
        final String SQL = "SELECT * FROM directors WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            if (resultSet.next()) {
                return Optional.of(
                        new Director(
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
    public void update(Director director) {
        directorDAO.update(DirectorMapper.mapper.toDirectorEntity(director));
    }

    @Override
    public void delete(int id) {
        directorDAO.delete(id);
    }

    @Override
    public Optional<Director> findByMovieId(int movieId) {
        final String SQL = """
            SELECT d.* FROM directors d 
            INNER JOIN  movies m ON m.director_id = d.id
            WHERE m.id = ?
            LIMIT 1
        """;
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            if (resultSet.next()) {
                return Optional.of(
                        new Director(
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
