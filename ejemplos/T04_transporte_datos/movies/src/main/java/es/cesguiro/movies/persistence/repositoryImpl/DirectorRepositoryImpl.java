package es.cesguiro.movies.persistence.repositoryImpl;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.repository.DirectorRepository;
import es.cesguiro.movies.mapper.DirectorMapper;
import es.cesguiro.movies.mapper.MovieMapper;
import es.cesguiro.movies.persistence.dao.DirectorDAO;
import es.cesguiro.movies.persistence.model.DirectorEntity;
import es.cesguiro.movies.persistence.model.MovieEntity;
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
        try (Connection connection = DBUtil.open(true)){
            return directorDAO.insert(connection, DirectorMapper.mapper.toDirectorEntity(director));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Director> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            Optional<DirectorEntity> directorEntity = directorDAO.find(connection, id);
            if(directorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(DirectorMapper.mapper.toDirector(directorEntity.get()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void update(Director director) {
        try(Connection connection= DBUtil.open(true)) {
            directorDAO.update(connection, DirectorMapper.mapper.toDirectorEntity(director));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection= DBUtil.open(true)) {
            directorDAO.delete(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Director> findByMovieId(int movieId) {
        try(Connection connection= DBUtil.open(true)) {
            Optional<DirectorEntity> directorEntity = directorDAO.findByMovieId(connection, movieId);
            if(directorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(DirectorMapper.mapper.toDirector(directorEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
