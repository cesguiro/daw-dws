package es.cesguiro.movies.persistence.dao.impl.jpa.mapper;

import es.cesguiro.movies.common.dto.MovieDto;
import es.cesguiro.movies.persistence.dao.impl.jpa.entity.MovieJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovieJpaMapper {

    MovieJpaMapper mapper = Mappers.getMapper(MovieJpaMapper.class);

    @Mapping(target = "directorDto", ignore = true)
    @Mapping(target = "characterMovieDtoList", ignore = true)
    MovieDto toMovieDto(MovieJpaEntity movieJpaEntity);

    @Mapping(target = "directorDto", expression = "java(DirectorJpaMapper.mapper.toDirectorDto(movieJpaEntity.getDirectorJpaEntity()))")
    @Mapping(target = "characterMovieDtoList", expression = "java(CharacterMovieJpaMapper.mapper.toCharacterMovieDtoList(movieJpaEntity.getCharacterMovieJpaEntityList()))")
    MovieDto toMovieDtoWithDirectorAndCharacterMovies(MovieJpaEntity movieJpaEntity);

    @Mapping(target = "directorJpaEntity", source = "directorDto")
    @Mapping(target = "characterMovieJpaEntityList", expression = "java(CharacterMovieJpaMapper.mapper.toCharacterMovieJpaEntityList(movieDto.getCharacterMovieDtoList()))")
    MovieJpaEntity toMovieJpaEntity(MovieDto movieDto);

}
