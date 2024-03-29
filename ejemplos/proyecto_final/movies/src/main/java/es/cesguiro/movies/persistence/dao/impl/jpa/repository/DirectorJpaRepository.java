package es.cesguiro.movies.persistence.dao.impl.jpa.repository;

import es.cesguiro.movies.persistence.dao.impl.jpa.entity.DirectorJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorJpaRepository extends JpaRepository<DirectorJpaEntity, Integer> {

}
