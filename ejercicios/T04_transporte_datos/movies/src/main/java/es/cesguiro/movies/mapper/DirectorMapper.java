package es.cesguiro.movies.mapper;

import es.cesguiro.movies.controller.model.director.DirectorCreateWeb;
import es.cesguiro.movies.controller.model.director.DirectorDetailWeb;
import es.cesguiro.movies.controller.model.director.DirectorUpdateWeb;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.persistence.model.DirectorEntity;
import es.cesguiro.movies.persistence.model.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);

    Director toDirector(DirectorCreateWeb directorCreateWeb);
    Director toDirector(DirectorUpdateWeb directorUpdateWeb);
    DirectorDetailWeb toDirectorDetailWeb(Director director);
    DirectorEntity toDirectorEntity(Director director);
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java(resultSet.getInt(\"deathYear\"))")
    DirectorEntity toDirectorEntity(ResultSet resultSet) throws SQLException;
    Director toDirector(DirectorEntity directorEntity);
}
