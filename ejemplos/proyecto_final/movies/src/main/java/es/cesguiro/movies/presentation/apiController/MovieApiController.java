package es.cesguiro.movies.presentation.apiController;

import es.cesguiro.movies.common.dto.CharacterMovieDto;
import es.cesguiro.movies.common.dto.MovieDto;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.presentation.apiController.http_response.Response;
import es.cesguiro.movies.presentation.apiController.mapper.CharacterMovieControllerMapper;
import es.cesguiro.movies.presentation.apiController.mapper.MovieControllerMapper;
import es.cesguiro.movies.presentation.apiController.request.CharacterMovieRequest;
import es.cesguiro.movies.presentation.apiController.request.MovieRequest;
import es.cesguiro.movies.presentation.apiController.response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

import static es.cesguiro.movies.common.dto.validation.Validation.validate;

@RequestMapping(MovieApiController.MOVIES)
@RestController
public class MovieApiController {

    @Value("${application.url}")
    private String urlBase;

    @Value("${page.size}")
    private int PAGE_SIZE;

    public static final String MOVIES = "/api/movies";

    private final MovieService movieService;


    @Autowired
    public MovieApiController(MovieService movieService) {
        this.movieService = movieService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : PAGE_SIZE;
        Stream<MovieDto> movieDTOStream = (page != null)? movieService.getAll(page, pageSize) : movieService.getAll();

        Stream<MovieResponse> movieResponseStream =
                movieDTOStream.map(
                        movieDto -> {
                            movieDto.setYear(null);
                            movieDto.setRuntime(null);
                            return MovieControllerMapper.mapper.toMovieResponse(movieDto);
                        }
        );
        long totalRecords = movieService.getTotalNumberOfRecords();
        Response response = Response.builder()
                .data(movieResponseStream)
                .totalRecords(totalRecords)
                .build();

        if(page != null) {
            response.paginate(page, pageSize, urlBase);
        }
        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        MovieResponse movieResponse = MovieControllerMapper
                .mapper
                .toMovieResponse(
                        movieService.find(id)
                );
        this.removeDataFromResponse(movieResponse);
        return Response
                .builder()
                .data(movieResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody MovieRequest movieRequest) {
        MovieDto movieDto = MovieControllerMapper
                .mapper
                .toMovieDto(movieRequest);
        validate(movieDto);
        MovieResponse movieResponse = MovieControllerMapper
                .mapper
                .toMovieResponse(
                        movieService
                                .create(movieDto)
                );
        this.removeDataFromResponse(movieResponse);
        return Response
                .builder()
                .data(movieResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") int id, @RequestBody MovieRequest movieRequest) {
        MovieDto movieDto = MovieControllerMapper.mapper.toMovieDto(movieRequest);
        validate(movieDto);
        movieDto.setId(id);
        MovieResponse movieResponse = MovieControllerMapper
                .mapper
                .toMovieResponse(
                        movieService
                                .update(movieDto)
                );
        this.removeDataFromResponse(movieResponse);
        //movieDto.setId(id);
        return Response
                .builder()
                .data(movieResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        movieService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/characters")
    public Response addCharacterMovie(@PathVariable("id") int id, @RequestBody CharacterMovieRequest characterMovieRequest){
        CharacterMovieDto characterMovieDto = CharacterMovieControllerMapper.mapper.toCharacterMovieDto(characterMovieRequest);
        MovieResponse movieResponse = MovieControllerMapper
                .mapper
                .toMovieResponse(
                        movieService
                                .addCharacterMovie(id, characterMovieDto)
                );
        this.removeDataFromResponse(movieResponse);
        return Response
                .builder()
                .data(movieResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/characters/{characterId}")
    public Response updateCharacterMovie(
            @PathVariable("id") int id,
            @PathVariable("characterId") int characterId,
            @RequestBody CharacterMovieRequest characterMovieRequest){
        CharacterMovieDto characterMovieDto = CharacterMovieControllerMapper.mapper.toCharacterMovieDto(characterMovieRequest);
        characterMovieDto.setId(characterId);
        MovieResponse movieResponse = MovieControllerMapper
                .mapper
                .toMovieResponse(
                        movieService
                                .updateCharacterMovie(id, characterMovieDto)
                );
        this.removeDataFromResponse(movieResponse);
        return Response
                .builder()
                .data(movieResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}/characters/{characterId}")
    public Response deleteCharacterMovie(
            @PathVariable("id") int id,
            @PathVariable("characterId") int characterId) {
        MovieResponse movieResponse = MovieControllerMapper
                .mapper
                .toMovieResponse(
                        movieService
                                .deleteCharacterMovie(id, characterId)
                );
        this.removeDataFromResponse(movieResponse);
        return Response
                .builder()
                .data(movieResponse)
                .build();
    }

    private void removeDataFromResponse(MovieResponse movieResponse) {
        movieResponse
                .getDirectorResponse()
                .setBirthYear(null);
        movieResponse
                .getDirectorResponse()
                .setDeathYear(null);
        movieResponse
                .getCharacterMovieResponseList()
                .stream()
                .forEach(
                        characterMovieResponse -> {
                            characterMovieResponse
                                    .getActorResponse()
                                    .setBirthYear(null);
                            characterMovieResponse
                                    .getActorResponse()
                                    .setDeathYear(null);
                        }
                );
    }



}
