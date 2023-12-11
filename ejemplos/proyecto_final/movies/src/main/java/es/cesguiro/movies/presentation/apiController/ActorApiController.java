package es.cesguiro.movies.presentation.apiController;

import es.cesguiro.movies.common.dto.ActorDto;
import es.cesguiro.movies.domain.service.ActorService;
import es.cesguiro.movies.presentation.apiController.http_response.Response;
import es.cesguiro.movies.presentation.apiController.mapper.ActorControllerMapper;
import es.cesguiro.movies.presentation.apiController.request.ActorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static es.cesguiro.movies.common.dto.validation.Validation.validate;

@RequestMapping(ActorApiController.ACTORS)
@RestController
public class ActorApiController {

    public static final String ACTORS = "/api/actors";

    final ActorService actorService;

    @Autowired
    public ActorApiController(ActorService actorService) {
        this.actorService = actorService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return Response
                .builder()
                .data(
                        ActorControllerMapper
                                .mapper
                                .toActorResponse(
                                        actorService
                                                .find(id)
                                )
                )
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody ActorRequest actorRequest){
        ActorDto actorDto = ActorControllerMapper.mapper.toActorDto(actorRequest);
        validate(actorDto);
        return Response
                .builder()
                .data(
                        ActorControllerMapper
                                .mapper
                                .toActorResponse(
                                        actorService.create(actorDto)
                                )
                )
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") int id, @RequestBody ActorRequest actorRequest) {
        ActorDto actorDto = ActorControllerMapper.mapper.toActorDto(actorRequest);
        actorDto.setId(id);
        validate(actorDto);
        return Response
                .builder()
                .data(
                        ActorControllerMapper
                                .mapper
                                .toActorResponse(
                                        actorService
                                                .update(actorDto)
                                )
                )
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        actorService.delete(id);
    }

}
