package es.cesguiro.movies.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DirectorDomainMapper {

    DirectorDomainMapper mapper = Mappers.getMapper(DirectorDomainMapper.class);

}
