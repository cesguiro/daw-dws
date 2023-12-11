package es.cesguiro.movies.persistence.dao.impl.jpa.mapper;

import es.cesguiro.movies.common.dto.CharacterMovieDto;
import es.cesguiro.movies.persistence.dao.impl.jpa.entity.CharacterMovieJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacterMovieJpaMapper {

    CharacterMovieJpaMapper mapper = Mappers.getMapper(CharacterMovieJpaMapper.class);

    @Mapping(target = "actorDto", source = "actorJpaEntity")
    CharacterMovieDto toCharacterMovieDto(CharacterMovieJpaEntity characterMovieJpaEntity);

    List<CharacterMovieDto> toCharacterMovieDtoList(List<CharacterMovieJpaEntity> characterMovieJpaEntityList);

    @Mapping(target = "actorJpaEntity", source = "actorDto")
    CharacterMovieJpaEntity toCharacterMovieJpaEntity(CharacterMovieDto characterMovieDto);

    List<CharacterMovieJpaEntity> toCharacterMovieJpaEntityList(List<CharacterMovieDto> characterMovieDtoList);

}
