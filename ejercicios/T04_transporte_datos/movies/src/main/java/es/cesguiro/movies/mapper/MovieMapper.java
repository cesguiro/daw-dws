package es.cesguiro.movies.mapper;

import es.cesguiro.movies.controller.model.actor.ActorListWeb;
import es.cesguiro.movies.controller.model.movie.MovieCreateWeb;
import es.cesguiro.movies.controller.model.movie.MovieDetailWeb;
import es.cesguiro.movies.controller.model.movie.MovieListWeb;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.dto.ActorDTO;
import es.cesguiro.movies.dto.MovieDTO;
import es.cesguiro.movies.persistence.model.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "year", expression = "java(resultSet.getInt(\"year\"))")
    @Mapping(target = "runtime", expression = "java(resultSet.getInt(\"runtime\"))")
    MovieEntity toMovieEntity(ResultSet resultSet) throws SQLException;

    MovieDTO toMovieDTO(MovieEntity movieEntity);
    Movie toMovie(MovieDTO movieDTO);

    @Mapping(target = "directorDTO", expression = "java(DirectorMapper.mapper.toDirectorDTO(movie.getDirector()))")
    @Mapping(target = "actorDTOs", expression = "java(mapActorsToActorDTOs(movie.getActors()))")
    MovieDTO toMovieDTO(Movie movie);

    @Named("actorDTOsToActorListWebs")
    default List<ActorDTO> mapActorsToActorDTOs(List<Actor> actors) {
        return actors.stream()
                .map(ActorMapper.mapper::toActorDTO)
                .toList();
    }

    MovieListWeb toMovieListWeb(MovieDTO movieDTO);
    @Mapping(target = "director", expression = "java(DirectorMapper.mapper.toDirectorListWeb(movieDTO.getDirectorDTO()))")
    @Mapping(target = "actors", expression = "java(mapActorDTOsToActorListWebs(movieDTO.getActorDTOs()))")
    MovieDetailWeb toMovieDetailWeb(MovieDTO movieDTO);

    @Named("actorDTOsToActorListWebs")
    default List<ActorListWeb> mapActorDTOsToActorListWebs(List<ActorDTO> actorDTOs) {
        return actorDTOs.stream()
                .map(ActorMapper.mapper::toActorListWeb)
                .toList();
    }

    MovieDTO toMovieDTO(MovieCreateWeb movieCreateWeb);

    @Mapping(target = "directorId", expression = "java(movieDTO.getDirectorDTO().getId())")
    @Mapping(target = "actorIds", expression = "java(mapActorDTOsToActorIds(movieDTO.getActorDTOs()))")
    MovieEntity toMovieEntity(MovieDTO movieDTO);

    @Named("actorDTOsToActorIds")
    default List<Integer> mapActorDTOsToActorIds(List<ActorDTO> actorDTOs) {
        return actorDTOs.stream()
                .map(actorDTO -> actorDTO.getId())
                .toList();
    }
}
