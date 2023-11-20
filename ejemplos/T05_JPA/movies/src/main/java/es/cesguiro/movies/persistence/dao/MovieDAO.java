package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.persistence.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDAO extends JpaRepository<MovieEntity, Integer> {

}
