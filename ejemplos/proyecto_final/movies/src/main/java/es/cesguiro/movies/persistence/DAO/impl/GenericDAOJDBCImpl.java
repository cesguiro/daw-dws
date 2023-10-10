package es.cesguiro.movies.persistence.DAO.impl;

import es.cesguiro.movies.db.DBUtil;
import es.cesguiro.movies.persistence.DAO.GenericDAO;
import es.cesguiro.movies.persistence.entity.GenericEntity;
import es.cesguiro.movies.persistence.factory.EntityFactory;
import es.cesguiro.movies.persistence.mapper.GenericMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericDAOJDBCImpl<T extends GenericEntity, ID> implements GenericDAO<T, ID> {

    protected GenericMapper<T> mapper;
    protected EntityFactory entityFactory;


    public GenericDAOJDBCImpl(GenericMapper<T> mapper, EntityFactory entityFactory) {
        this.mapper = mapper;
        this.entityFactory = entityFactory;
    }


    @Override
    public Optional<List<T>> findAll() {
        String sql = "SELECT * FROM " + entityFactory.createEntity().getTable();
        /*if(page != null) {
            sql += String.format(" LIMIT %d, %d", (page -1) * pageSize, pageSize);
        }*/
        //String sql = String.format("SELECT * FROM movies LIMIT %d, %d", (page - 1) * pageSize, pageSize);
        List<T> entities = new ArrayList<>();
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, sql, null);
            if(!resultSet.next()) {
                return Optional.empty();
            }
            do {
                entities.add(mapper.mapRow(resultSet, 1));
            } while (resultSet.next());
            return Optional.of(entities);
        }  catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<T> read(ID id) {
        final String SQL = "SELECT * FROM " + entityFactory.createEntity().getTable() + " WHERE " + entityFactory.createEntity().getPrimaryKey() + " = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            DBUtil.close(connection);
            if(resultSet.next()) {
                return Optional.of(mapper.mapRow(resultSet, 1));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /*@Override
    public Object create(GenericEntity entity) {
        return null;
    }


    @Override
    public void update(GenericEntity entity) {

    }

    @Override
    public void delete(Object o) {

    }*/

}
