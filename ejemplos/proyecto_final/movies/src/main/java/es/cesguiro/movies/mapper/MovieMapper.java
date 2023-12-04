package es.cesguiro.movies.mapper;

import es.cesguiro.movies.controller.model.character.CharacterMovieCreateWeb;
import es.cesguiro.movies.controller.model.character.CharacterMovieListWeb;
import es.cesguiro.movies.controller.model.movie.MovieCreateWeb;
import es.cesguiro.movies.controller.model.movie.MovieDetailWeb;
import es.cesguiro.movies.controller.model.movie.MovieListWeb;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.CharacterMovie;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.persistence.model.CharacterMovieEntity;
import es.cesguiro.movies.persistence.model.MovieEntity;
import org.mapstruct.IterableMapping;
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

    List<MovieListWeb> toMovieListWebs(List<Movie> movies);

    @Mapping(target = "characterMovies", expression = "java(CharacterMovieMapper.mapper.toCharacterMovieListWeb(movie.getCharacterMovies()))")
    MovieDetailWeb toMovieDetailWeb(Movie movie);

    /*@Mapping(target = "director", expression = "java(DirectorMapper.mapper.toDirector(movieEntity.getDirectorEntity()))")
    @Mapping(target = "characterMovies", expression = "java(CharacterMovieMapper.mapper.toCharacterMovies(movieEntity.getCharacterMovieEntities()))")*/
    @Mapping(target = "director", ignore = true)
    @Mapping(target = "characterMovies", ignore = true)
    @Named("toMovie")
    Movie toMovie(MovieEntity movieEntity);

    @Mapping(target = "director", ignore = true)
    @Mapping(target = "characterMovies", ignore = true)
    @IterableMapping(qualifiedByName = "toMovie")
    @Named("toMovieList")
    List<Movie> toMovieList(List<MovieEntity> movieEntities);

    @Mapping(target = "director", expression = "java(DirectorMapper.mapper.toDirector(movieEntity.getDirectorEntity()))")
    @Mapping(target = "characterMovies", expression = "java(CharacterMovieMapper.mapper.toCharacterMovies(movieEntity.getCharacterMovieEntities()))")
    @Named("toMovieWithDirectorAndCharacterMovies")
    Movie toMovieWithDirectorAndCharacterMovies(MovieEntity movieEntity);

    @Mapping(target = "director", expression = "java(DirectorMapper.mapper.toDirector(movieCreateWeb.getDirectorId()))")
    @Mapping(target = "characterMovies", expression = "java(mapCharactersMoviesCreateWebsToCharacterMovies(movieCreateWeb.getCharacters()))")
    Movie toMovie(MovieCreateWeb movieCreateWeb);


    @Named("mapCharactersMoviesCreateWebsToCharacterMovies")
    default List<CharacterMovie> mapCharactersMoviesCreateWebsToCharacterMovies(List<CharacterMovieCreateWeb> characterMovieCreateWebs) {
        return characterMovieCreateWebs.stream()
                .map(characterMovieCreateWeb -> CharacterMovieMapper.mapper.toCharacterMovie(
                        characterMovieCreateWeb.getActorId(),
                        characterMovieCreateWeb.getCharacters()))
                .toList();
    }

    @Mapping(target = "directorEntity", expression = "java(DirectorMapper.mapper.toDirectorEntity(movie.getDirector()))")
    MovieEntity toMovieEntity(Movie movie);


    /*@Named("actorToActorIds")
    default List<Integer> mapActorsToActorIds(List<Actor> actors) {
        return actors.stream()
                .map(actor -> actor.getId())
                .toList();
    }*/

}
