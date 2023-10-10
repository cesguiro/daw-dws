package es.cesguiro.movies.persistence.repository.impl;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.persistence.repository.MovieRepository;
import es.cesguiro.movies.persistence.DAO.MovieDAO;
import es.cesguiro.movies.persistence.entity.impl.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Autowired
    MovieDAO movieDAO;

    @Override
    public Optional<List<MovieEntity>> getAll(Integer page, Integer pageSize) {
        return movieDAO.findAll();
    }

    @Override
    public Optional<MovieEntity> find(int id) {
        return movieDAO.read(id);
    }

    @Override
    public int getTotalNumberOfRecords() {
        final String SQL = "SELECT COUNT(*) FROM movies";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            DBUtil.close(connection);
            resultSet.next();
            return (int) resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("SQL: " + SQL);
        }
    }
}
