package es.cesguiro.movies.persistence.DAO.impl;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.persistence.DAO.DirectorDAO;
import es.cesguiro.movies.persistence.entity.impl.DirectorEntity;
import es.cesguiro.movies.persistence.factory.impl.DirectorFactory;
import es.cesguiro.movies.persistence.mapper.impl.DirectorMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class DirectorDAOJDBCImpl extends GenericDAOJDBCImpl<DirectorEntity, Integer> implements DirectorDAO {

    public DirectorDAOJDBCImpl() {
        super(new DirectorMapper(), new DirectorFactory());
    }

    @Override
    public Optional<DirectorEntity> findByMovieId(int movieId) {
        final String SQL = "SELECT * FROM directors WHERE id = (SELECT director_id FROM movies WHERE id = ?) LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            DBUtil.close(connection);
            if(resultSet.next()) {
                return Optional.of(mapper.mapRow(resultSet, 1));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
