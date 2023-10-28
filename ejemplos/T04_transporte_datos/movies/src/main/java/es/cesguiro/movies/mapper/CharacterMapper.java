package es.cesguiro.movies.mapper;

import es.cesguiro.movies.domain.entity.CharacterMovie;
import es.cesguiro.movies.persistence.model.CharacterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterMapper mapper = Mappers.getMapper(CharacterMapper.class);

    CharacterMovie toCharacterMovie(CharacterEntity characterEntity);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "characters", expression = "java(resultSet.getString(\"characters\"))")
    CharacterEntity toCharacterEntity(ResultSet resultSet) throws SQLException;

    /*@Named("characterIdToCharactersEntities")
    default List<CharacterEntity> mapCharacterIdsToCharacterEntities(List<Integer> characterIds) {
        return characterEntities.stream()
                .map(CharacterMapper.mapper::toCharacterMovie)
                .toList();
    }*/
}
