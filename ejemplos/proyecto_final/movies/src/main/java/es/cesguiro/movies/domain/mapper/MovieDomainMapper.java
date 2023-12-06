package es.cesguiro.movies.domain.mapper;

import es.cesguiro.movies.common.dto.MovieDto;
import es.cesguiro.movies.domain.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovieDomainMapper {

    MovieDomainMapper mapper = Mappers.getMapper(MovieDomainMapper.class);

    @Mapping(target = "directorDto", source = "director")
    MovieDto toMovieDto(Movie movie);

}
