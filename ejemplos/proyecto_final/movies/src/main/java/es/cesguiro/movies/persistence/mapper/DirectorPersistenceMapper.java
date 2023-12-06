package es.cesguiro.movies.persistence.mapper;

import es.cesguiro.movies.common.dto.DirectorDto;
import es.cesguiro.movies.domain.entity.Director;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DirectorPersistenceMapper {

    DirectorPersistenceMapper mapper = Mappers.getMapper(DirectorPersistenceMapper.class);

    Director toDirector(DirectorDto directorDto);
}
