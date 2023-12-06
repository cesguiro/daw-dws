package es.cesguiro.movies.presentation.apiController.mapper;

import es.cesguiro.movies.presentation.apiController.response.DirectorResponse;
import es.cesguiro.movies.common.dto.DirectorDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DirectorControllerMapper {

    DirectorControllerMapper mapper = Mappers.getMapper(DirectorControllerMapper.class);

    DirectorResponse toDirectorResponse(DirectorDto directorDTO);

}
