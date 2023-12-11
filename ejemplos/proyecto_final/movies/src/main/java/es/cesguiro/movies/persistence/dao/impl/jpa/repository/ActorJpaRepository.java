package es.cesguiro.movies.persistence.dao.impl.jpa.repository;

import es.cesguiro.movies.persistence.dao.impl.jpa.entity.ActorJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ActorJpaRepository extends JpaRepository<ActorJpaEntity, Integer> {


    /*@Query("SELECT a.actorJpaEntity FROM CharacterMovieJpaEntity a WHERE a.id = ?1")
    ActorJpaEntity findByCharacterId(int characterId);*/
}
