package es.cesguiro.movies.domain.mapper;

import es.cesguiro.movies.common.dto.DirectorDto;
import es.cesguiro.movies.domain.entity.Director;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DirectorDomainMapper {

    DirectorDomainMapper mapper = Mappers.getMapper(DirectorDomainMapper.class);

    DirectorDto toDirectorDto(Director director);

    Director toDirector(DirectorDto directorDto);

    void updateDirectorFromDirectorDto(DirectorDto directorDto, @MappingTarget Director director);
}
