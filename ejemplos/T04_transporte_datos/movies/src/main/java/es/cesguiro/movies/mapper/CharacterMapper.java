package es.cesguiro.movies.mapper;

import es.cesguiro.movies.persistence.model.CharacterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterMapper mapper = Mappers.getMapper(CharacterMapper.class);

    Character toCharacter(CharacterEntity characterEntity);
}
