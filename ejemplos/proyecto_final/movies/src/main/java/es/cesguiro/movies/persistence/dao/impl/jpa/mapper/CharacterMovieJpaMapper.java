package es.cesguiro.movies.persistence.dao.impl.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CharacterMovieJpaMapper {

    CharacterMovieJpaMapper mapper = Mappers.getMapper(CharacterMovieJpaMapper.class);
}
