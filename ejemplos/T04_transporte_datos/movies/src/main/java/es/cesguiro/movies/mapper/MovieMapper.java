package es.cesguiro.movies.mapper;

import es.cesguiro.movies.controller.model.character.CharacterMovieListWeb;
import es.cesguiro.movies.controller.model.movie.MovieCreateWeb;
import es.cesguiro.movies.controller.model.movie.MovieDetailWeb;
import es.cesguiro.movies.controller.model.movie.MovieListWeb;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.CharacterMovie;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.persistence.model.CharacterMovieEntity;
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

    MovieListWeb toMovieListWeb(Movie movie);

    @Mapping(target = "characterMovies", expression = "java(mapCharacterMoviesToCharacterMovieListWeb(movie.getCharacterMovies()))")
    MovieDetailWeb toMovieDetailWeb(Movie movie);

    @Named("characterMoviesToCharacterMovieListWeb")
    default List<CharacterMovieListWeb> mapCharacterMoviesToCharacterMovieListWeb(List<CharacterMovie> characterMovies) {
        return characterMovies.stream()
                .map(CharacterMovieMapper.mapper::toCharacterMovieListWeb)
                .toList();
    }


    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "year", expression = "java(resultSet.getInt(\"year\"))")
    @Mapping(target = "runtime", expression = "java(resultSet.getInt(\"runtime\"))")
    MovieEntity toMovieEntity(ResultSet resultSet) throws SQLException;

    @Mapping(target = "director", expression = "java(DirectorMapper.mapper.toDirector(movieEntity.getDirectorEntity()))")
    @Mapping(target = "characterMovies", expression = "java(mapCharacterMovieEntitiesToCharacterMovies(movieEntity.getCharacterMovieEntities()))")
    Movie toMovie(MovieEntity movieEntity);

    @Named("characterEntitiesToCharacters")
    default List<CharacterMovie> mapCharacterMovieEntitiesToCharacterMovies(List<CharacterMovieEntity> characterMovieEntities) {
        if(characterMovieEntities == null) {
            return null;
        }
        return characterMovieEntities.stream()
                .map(CharacterMovieMapper.mapper::toCharacterMovie)
                .toList();
    }

    @Mapping(target = "director", ignore = true)
    @Mapping(target = "characterMovies", ignore = true)
    Movie toMovie(MovieCreateWeb movieCreateWeb);


    MovieEntity toMovieEntity(Movie movie);


    @Named("actorToActorIds")
    default List<Integer> mapActorsToActorIds(List<Actor> actors) {
        return actors.stream()
                .map(actor -> actor.getId())
                .toList();
    }

}
