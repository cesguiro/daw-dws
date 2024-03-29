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


    List<MovieEntity> findByDirectorEntityId(int directorId);
}
