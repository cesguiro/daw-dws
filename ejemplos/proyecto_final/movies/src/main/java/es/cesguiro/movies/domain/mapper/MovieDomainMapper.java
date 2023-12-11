package es.cesguiro.movies.domain.mapper;

import es.cesguiro.movies.common.dto.MovieDto;
import es.cesguiro.movies.domain.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovieDomainMapper {

    MovieDomainMapper mapper = Mappers.getMapper(MovieDomainMapper.class);

    @Mapping(target = "directorDto", source = "director")
    @Mapping(target = "characterMovieDtoList", expression = "java(CharacterMovieDomainMapper.mapper.toCharacterMovieDtoList(movie.getCharacterMovieList()))")
    MovieDto toMovieDto(Movie movie);

    @Mapping(target = "director", source = "directorDto")
    @Mapping(target = "characterMovieList", expression = "java(CharacterMovieDomainMapper.mapper.toCharacterMovieList(movieDto.getCharacterMovieDtoList()))")
    Movie toMovie(MovieDto movieDto);

    void updataMovieFromMovieDto(MovieDto movieDto, @MappingTarget Movie movie);
}
