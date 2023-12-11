package es.cesguiro.movies.domain.mapper;

import es.cesguiro.movies.common.dto.CharacterMovieDto;
import es.cesguiro.movies.domain.entity.CharacterMovie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacterMovieDomainMapper {
    CharacterMovieDomainMapper mapper = Mappers.getMapper(CharacterMovieDomainMapper.class);

    @Mapping(target = "actor", expression = "java(ActorDomainMapper.mapper.toActor(characterMovieDto.getActorDto()))")
    CharacterMovie toCharacterMovie(CharacterMovieDto characterMovieDto);

    @Mapping(target = "actorDto", source = "actor")
    CharacterMovieDto toCharacterMovieDto(CharacterMovie characterMovie);

    List<CharacterMovieDto> toCharacterMovieDtoList(List<CharacterMovie> characterMovieList);

    List<CharacterMovie> toCharacterMovieList(List<CharacterMovieDto> characterMovieDtoList);

    void updataCharacterMovieFromCharacterMovieDto(CharacterMovieDto characterMovieDto, @MappingTarget CharacterMovie characterMovie);

}
