package es.cesguiro.movies.domain.mapper;

import es.cesguiro.movies.controller.DTO.movie.MovieDetailDTO;
import es.cesguiro.movies.controller.DTO.movie.MovieListDTO;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.persistence.entity.impl.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneralMapper {

    MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);

    default Movie MovieEntityToMovie(MovieEntity movieEntity) {
        return movieMapper.fromEntityToModel(movieEntity);
    }

    default List<Movie> MovieEntitiesToMovies(List<MovieEntity> entities) {
        return movieMapper.fromEntityListToModelList(entities);
    }

    default MovieDetailDTO MovieToMovieDetailDTO(Movie movie) {
        return movieMapper.fromModelToDTO(movie);
    }

    default List<MovieListDTO> MoviesToMovieListDTO(List<Movie> movies) {
        return movieMapper.fromModelListToDTOList(movies);
    }

    /*public Object map(Object from, Object to) {
        switch (from.getClass()) {
            case "GenericEntity":
                return null;
                break;
        }
        return null;
    }*/

    /*public Movie fromEntityToModel(GenericEntity entity) {
        return null;
    }

    public MovieDetailDTO fromModelToDTO(Movie model) {
        return null;
    }

    public Movie fromDTOToModel(MovieDetailDTO dto) {
        return null;
    }

    public GenericEntity fromModelToEntity(Movie model) {
        return null;
    }*/
}
