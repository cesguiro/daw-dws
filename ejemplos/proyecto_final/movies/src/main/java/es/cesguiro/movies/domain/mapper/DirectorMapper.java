package es.cesguiro.movies.domain.mapper;

import es.cesguiro.movies.controller.DTO.director.DirectorDetailDTO;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.persistence.entity.impl.DirectorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);

    Director toModel(DirectorEntity entity);
    DirectorDetailDTO toDetailDTO(Director director);
    //MovieListDTO toListDTO(Movie movie);
}
