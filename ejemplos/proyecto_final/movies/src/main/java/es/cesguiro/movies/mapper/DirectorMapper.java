package es.cesguiro.movies.mapper;

import es.cesguiro.movies.controller.model.director.DirectorCreateWeb;
import es.cesguiro.movies.controller.model.director.DirectorDetailWeb;
import es.cesguiro.movies.controller.model.director.DirectorUpdateWeb;
import es.cesguiro.movies.controller.response.DirectorResponse;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.dto.DirectorDTO;
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

    DirectorDTO toDirectorDTO(DirectorEntity directorEntity);

    DirectorResponse toDirectorResponse(DirectorDTO directorDTO);

    /** Antiguos **/

    @Mapping(target = "id", source = "directorId")
    Director toDirector(Integer directorId);

    Director toDirector(DirectorCreateWeb directorCreateWeb);
    Director toDirector(DirectorUpdateWeb directorUpdateWeb);
    DirectorDetailWeb toDirectorDetailWeb(Director director);
    DirectorEntity toDirectorEntity(Director director);

    Director toDirector(DirectorEntity DirectorEntity);
}
