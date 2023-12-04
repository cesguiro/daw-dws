package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.persistence.model.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DirectorDAO extends JpaRepository<DirectorEntity, Integer> {

}
