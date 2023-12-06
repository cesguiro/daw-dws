package es.cesguiro.movies.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CharacterMovieMapper {
    es.cesguiro.movies.domain.mapper.CharacterMovieMapper mapper = Mappers.getMapper(es.cesguiro.movies.domain.mapper.CharacterMovieMapper.class);

}
