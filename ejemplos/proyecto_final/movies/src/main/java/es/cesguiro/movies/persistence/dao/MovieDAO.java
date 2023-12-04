package es.cesguiro.movies.persistence.dao;

import es.cesguiro.movies.persistence.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDAO extends JpaRepository<MovieEntity, Integer> {

    @Modifying
    @Query(value = "INSERT INTO actors_movies (movie_id, actor_id, characters) VALUES (:movieId, :actorId, :characters)", nativeQuery = true)
    void addCharactersToMovie(@Param("movieId") int movieId, @Param("actorId") int actorId, @Param("characters") String characters);

    List<MovieEntity> findByDirectorEntityId(int directorId);
}
