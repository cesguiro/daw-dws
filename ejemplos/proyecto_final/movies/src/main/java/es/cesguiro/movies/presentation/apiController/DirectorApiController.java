package es.cesguiro.movies.presentation.apiController;

import es.cesguiro.movies.common.dto.DirectorDto;
import es.cesguiro.movies.common.dto.MovieDto;
import es.cesguiro.movies.domain.service.DirectorService;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.presentation.apiController.http_response.Response;
import es.cesguiro.movies.presentation.apiController.mapper.DirectorControllerMapper;
import es.cesguiro.movies.presentation.apiController.mapper.MovieControllerMapper;
import es.cesguiro.movies.presentation.apiController.request.DirectorRequest;
import es.cesguiro.movies.presentation.apiController.response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

import static es.cesguiro.movies.common.dto.validation.Validation.validate;

@RequestMapping(DirectorApiController.DIRECTORS)
@RestController
public class DirectorApiController {

    public static final String DIRECTORS = "/api/directors";

    final DirectorService directorService;
    final MovieService movieService;

    @Autowired
    public DirectorApiController(DirectorService directorService, MovieService movieService) {
        this.directorService = directorService;
        this.movieService = movieService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return Response.builder().data(DirectorControllerMapper.mapper.toDirectorResponse(directorService.find(id))).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody DirectorRequest directorRequest){
        //DirectorDto directorDto = mapToDirectorDtoWithValidation(directorRequest);
        DirectorDto directorDto = DirectorControllerMapper.mapper.toDirectorDto(directorRequest);
        validate(directorDto);

        return Response
                .builder()
                .data(
                        DirectorControllerMapper
                                .mapper
                                .toDirectorResponse(
                                        directorService
                                                .create(directorDto)
                                )
                )
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") int id, @RequestBody DirectorRequest directorRequest) {
        //DirectorDto directorDto = mapToDirectorDtoWithValidation(directorRequest);
        DirectorDto directorDto = DirectorControllerMapper.mapper.toDirectorDto(directorRequest);
        validate(directorDto);

        directorDto.setId(id);
        return Response
                .builder()
                .data(
                        DirectorControllerMapper
                                .mapper
                                .toDirectorResponse(
                                        directorService
                                                .update(directorDto)
                                )
                )
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        directorService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/movies")
    public Response findMoviesByDirectorId(@PathVariable("id") int id) {
        Stream<MovieDto> movieDtoStream = movieService.findByDirectorId(id);
        Stream<MovieResponse> movieResponseStream =
                movieDtoStream.map(
                        movieDto -> {
                            movieDto.setYear(null);
                            movieDto.setRuntime(null);
                            return MovieControllerMapper.mapper.toMovieResponse(movieDto);
                        }
                );
        long totalRecords = movieService.getTotalNumberOfRecords();
        return Response.builder()
                .data(movieResponseStream)
                .totalRecords(totalRecords)
                .build();

    }

}
