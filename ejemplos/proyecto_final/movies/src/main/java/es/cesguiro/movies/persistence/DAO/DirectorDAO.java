package es.cesguiro.movies.persistence.DAO;

import es.cesguiro.movies.persistence.entity.impl.DirectorEntity;

import java.util.Optional;

public interface DirectorDAO extends GenericDAO<DirectorEntity, Integer> {

    Optional<DirectorEntity> findByMovieId(int movieId);
}
