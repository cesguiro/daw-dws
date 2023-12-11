package es.cesguiro.movies.persistence.dao.impl.jpa.mapper;

import es.cesguiro.movies.common.dto.DirectorDto;
import es.cesguiro.movies.persistence.dao.impl.jpa.entity.DirectorJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DirectorJpaMapper {

    DirectorJpaMapper mapper = Mappers.getMapper(DirectorJpaMapper.class);


    DirectorDto toDirectorDto(DirectorJpaEntity directorJpaEntity);

    DirectorJpaEntity toDirectorEntity(DirectorDto directorDto);
}
