package es.cesguiro.movies.dto.mapper;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.dto.movie.MovieDetailDTO;
import es.cesguiro.movies.dto.movie.MovieListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    MovieDetailDTO toDetailDTO(Movie movie);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "year", expression = "java(resultSet.getInt(\"year\"))")
    @Mapping(target = "runtime", expression = "java(resultSet.getInt(\"runtime\"))")
    MovieDetailDTO toDetailDTO(ResultSet resultSet) throws SQLException;
    MovieListDTO toListDTO(Movie movie);
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "year", expression = "java(resultSet.getInt(\"year\"))")
    MovieListDTO toListDTO(ResultSet resultSet) throws SQLException;

}
