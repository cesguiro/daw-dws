package es.cesguiro.movies.mapper;

import es.cesguiro.movies.controller.model.movie.MovieCreateWeb;
import es.cesguiro.movies.controller.model.movie.MovieDetailWeb;
import es.cesguiro.movies.controller.model.movie.MovieListWeb;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.persistence.model.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    MovieListWeb toMovieListWeb(Movie movie);
    MovieDetailWeb toMovieDetailWeb(Movie movie);
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "year", expression = "java(resultSet.getInt(\"year\"))")
    @Mapping(target = "runtime", expression = "java(resultSet.getInt(\"runtime\"))")
    MovieEntity toMovieEntity(ResultSet resultSet) throws SQLException;
    Movie toMovie(MovieEntity movieEntity);

    @Mapping(target = "director", ignore = true)
    @Mapping(target = "actors", ignore = true)
    Movie toMovie(MovieCreateWeb movieCreateWeb);


    @Mapping(target = "directorId", expression = "java(movie.getDirector().getId())")
    MovieEntity toMovieEntity(Movie movie);

    /*@Mapping(target = "director.id", source = "directorId")
    @Mapping(target = "actors", source = "actorIds", qualifiedByName = "actorIdsToActors")
    Movie toMovie(MovieCreateWeb movieCreateWeb);

    @Named("actorIdsToActors")
    default List<Actor> mapActorIdsToActors(List<Integer> actorIds) {
        return actorIds.stream()
                .map(id -> {
                    Actor actor = new Actor();
                    actor.setId(id);
                    return actor;
                })
                .toList();
    }*/

    /*MovieDetailDTO toDetailDTO(ResultSet resultSet) throws SQLException;
    MovieListDTO toListDTO(Movie movie);
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "year", expression = "java(resultSet.getInt(\"year\"))")
    MovieListDTO toListDTO(ResultSet resultSet) throws SQLException;*/

}
