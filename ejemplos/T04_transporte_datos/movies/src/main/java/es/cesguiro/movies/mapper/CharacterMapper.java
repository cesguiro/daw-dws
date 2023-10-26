package es.cesguiro.movies.mapper;

import es.cesguiro.movies.persistence.model.CharacterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterMapper mapper = Mappers.getMapper(CharacterMapper.class);

    Character toCharacter(CharacterEntity characterEntity);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "characters", expression = "java(resultSet.getString(\"characters\"))")
    CharacterEntity toCharacterEntity(ResultSet resultSet) throws SQLException;
}
