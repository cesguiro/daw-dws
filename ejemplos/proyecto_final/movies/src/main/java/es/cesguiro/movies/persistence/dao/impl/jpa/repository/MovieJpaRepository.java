package es.cesguiro.movies.persistence.dao.impl.jpa.repository;

import es.cesguiro.movies.persistence.dao.impl.jpa.entity.MovieJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MovieJpaRepository extends JpaRepository<MovieJpaEntity, Integer> {
    List<MovieJpaEntity> findByDirectorJpaEntityId(int directorId);

    /*@Modifying
    @Query(value = "INSERT INTO actors_movies (movie_id, actor_id, characters) VALUES (:movieId, :actorId, :characters)", nativeQuery = true)
    void addCharactersToMovie(@Param("movieId") int movieId, @Param("actorId") int actorId, @Param("characters") String characters);*/

    /*@Modifying
    @Query(value = "UPDATE movies SET title = :title, year = :year, runtime = :runtime, director_id = :directorId WHERE id = :movieId", nativeQuery = true)
    void update(
            @Param("movieId") int movieId,
            @Param("title") String title,
            @Param("year") int year,
            @Param("runtime") int runtime,
            @Param("directorId") int directorId
    );*/


    //List<MovieJpaEntity> findByDirectorEntityId(int directorId);
}
