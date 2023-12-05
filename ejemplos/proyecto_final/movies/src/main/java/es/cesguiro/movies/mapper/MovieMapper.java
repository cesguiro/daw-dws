package es.cesguiro.movies.mapper;

import es.cesguiro.movies.controller.model.character.CharacterMovieCreateWeb;
import es.cesguiro.movies.controller.model.character.CharacterMovieListWeb;
import es.cesguiro.movies.controller.model.movie.MovieCreateWeb;
import es.cesguiro.movies.controller.model.movie.MovieDetailWeb;
import es.cesguiro.movies.controller.model.movie.MovieListWeb;
import es.cesguiro.movies.controller.model.movie.MovieUpdateWeb;
import es.cesguiro.movies.controller.response.MovieResponse;
import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.CharacterMovie;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.dto.MovieDTO;
import es.cesguiro.movies.persistence.model.CharacterMovieEntity;
import es.cesguiro.movies.persistence.model.MovieEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;


@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    @Named("toMovieDTO")
    MovieDTO toMovieDTO(MovieEntity movieEntity);

    @Mapping(target = "directorDTO", expression = "java(DirectorMapper.mapper.toDirectorDTO(movieEntity.getDirectorEntity()))")
    @Mapping(target = "characterMovieDTOList", expression = "java(CharacterMovieMapper.mapper.toCharacterMovieDTOList(movieEntity.getCharacterMovieEntities()))")
    @Named("toMovieDTOWithDirectorAndCharacterMovies")
    MovieDTO toMovieDTOWithDirectorAndCharacterMovies(MovieEntity movieEntity);

    @IterableMapping(qualifiedByName = "toMovieDTO")
    Stream<MovieDTO> toMovieDTOStream(Stream<MovieEntity> movieEntityList);

    @Mapping(target = "directorResponse", expression = "java(DirectorMapper.mapper.toDirectorResponse(movieDTO.getDirectorDTO()))")
    @Mapping(target = "characterMovieResponseList", expression = "java(CharacterMovieMapper.mapper.toCharacterMovieResponseList(movieDTO.getCharacterMovieDTOList()))")
    MovieResponse toMovieResponse(MovieDTO movieDTO);

    Stream<MovieResponse> toMovieResponseStream(Stream<MovieDTO> movieDTOStream);

    /** Antiguo **/

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

    @Mapping(target = "director", expression = "java(DirectorMapper.mapper.toDirector(movieUpdateWeb.getDirectorId()))")
    Movie toMovie(MovieUpdateWeb movieUpdateWeb);

    @Named("mapCharactersMoviesCreateWebsToCharacterMovies")
    default List<CharacterMovie> mapCharactersMoviesCreateWebsToCharacterMovies(List<CharacterMovieCreateWeb> characterMovieCreateWebs) {
        return characterMovieCreateWebs.stream()
                .map(characterMovieCreateWeb -> CharacterMovieMapper.mapper.toCharacterMovie(
                        characterMovieCreateWeb.getActorId(),
                        characterMovieCreateWeb.getCharacters()))
                .toList();
    }

    @Mapping(target = "directorEntity", expression = "java(DirectorMapper.mapper.toDirectorEntity(movie.getDirector()))")
    @Mapping(target = "characterMovieEntities", expression = "java(CharacterMovieMapper.mapper.toCharacterMovieEntities(movie.getCharacterMovies()))")
    MovieEntity toMovieEntity(Movie movie);

    /*@Mapping(target = "directorEntity", expression = "java(DirectorMapper.mapper.toDirectorEntity(movie.getDirector()))")
    @Mapping(target = "characterMovieEntities", expression = "java(mapCharactersMoviesToCharacterMovieEntities(movie.getCharacterMovies()))")
    MovieEntity toMovieEntity(Movie movie);

    @Named("mapCharactersMoviesToCharacterMovieEntities")
    default List<CharacterMovieEntity> mapCharactersMoviesToCharacterMovieEntities(List<CharacterMovie> characterMovies) {
        return characterMovies.stream()
                .map(CharacterMovieMapper.mapper::toCharacterMovieEntity)
                .toList();
    }*/


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "characterMovies", ignore = true)
    void updateMovieFromMovieUpdate(Movie movie, @MappingTarget Movie existingMovie);

}
