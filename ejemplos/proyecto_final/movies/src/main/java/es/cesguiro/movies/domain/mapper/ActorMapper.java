package es.cesguiro.movies.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    es.cesguiro.movies.domain.mapper.ActorMapper mapper = Mappers.getMapper(es.cesguiro.movies.domain.mapper.ActorMapper.class);

}
