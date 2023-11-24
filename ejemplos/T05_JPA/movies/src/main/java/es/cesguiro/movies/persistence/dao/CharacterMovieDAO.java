package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.persistence.model.CharacterMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CharacterMovieDAO extends JpaRepository<CharacterMovieEntity, Integer> {

}
