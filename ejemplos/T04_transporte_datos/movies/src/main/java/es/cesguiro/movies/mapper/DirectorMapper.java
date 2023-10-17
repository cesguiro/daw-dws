package es.cesguiro.movies.mapper;

import es.cesguiro.movies.controller.model.director.DirectorCreateWeb;
import es.cesguiro.movies.controller.model.director.DirectorDetailWeb;
import es.cesguiro.movies.controller.model.director.DirectorUpdateWeb;
import es.cesguiro.movies.domain.entity.Director;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);

    Director toDirector(DirectorCreateWeb directorCreateWeb);
    Director toDirector(DirectorUpdateWeb directorUpdateWeb);
    DirectorDetailWeb toDirectorDetailWeb(Director director);
}
