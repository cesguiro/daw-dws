package es.cesguiro.movies.presentation.apiController.mapper;

import es.cesguiro.movies.presentation.apiController.response.ActorResponse;
import es.cesguiro.movies.common.dto.ActorDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ActorControllerMapper {

    ActorControllerMapper mapper = Mappers.getMapper(ActorControllerMapper.class);

    ActorResponse toActorResponse(ActorDto actorDTO);
}
