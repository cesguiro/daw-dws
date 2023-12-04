package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.persistence.model.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ActorDAO extends JpaRepository<ActorEntity, Integer> {

}
