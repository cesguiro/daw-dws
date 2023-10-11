package es.cesguiro.movies.persistence.DAO;

import es.cesguiro.movies.persistence.entity.GenericEntity;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T extends GenericEntity, ID> {

    public Optional<List<T>> findAll();
    //public ID create(T entity);
    public Optional<T> read(ID id);
    //public void update(T entity);
    //public void delete(ID id);
}
