package es.cesguiro.movies.persistence.dao.impl.jpa.repository;

import es.cesguiro.movies.persistence.dao.impl.jpa.entity.CharacterMovieJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CharacterMovieJpaRepository extends JpaRepository<CharacterMovieJpaEntity, Integer> {

}
