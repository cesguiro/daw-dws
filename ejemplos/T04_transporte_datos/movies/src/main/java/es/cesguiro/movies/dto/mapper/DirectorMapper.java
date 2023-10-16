package es.cesguiro.movies.dto.mapper;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.dto.director.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);

    DirectorDetailDTO toDetailDTO(Director director);
    DirectorListDTO toListDTO(Director director);
    Director toModel(DirectorCommandDTO directorCommandDTO);

}
