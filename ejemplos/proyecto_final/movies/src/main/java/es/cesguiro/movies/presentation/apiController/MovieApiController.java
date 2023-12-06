package es.cesguiro.movies.presentation.apiController;

import es.cesguiro.movies.presentation.apiController.mapper.MovieControllerMapper;
import es.cesguiro.movies.presentation.apiController.response.MovieResponse;
import es.cesguiro.movies.common.dto.CharacterMovieDto;
import es.cesguiro.movies.common.dto.MovieDto;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.presentation.apiController.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

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
                        movieDTO -> {
                            movieDTO.setYear(null);
                            movieDTO.setRuntime(null);
                            return MovieControllerMapper.mapper.toMovieResponse(movieDTO);
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
        MovieResponse movieResponse = MovieControllerMapper.mapper.toMovieResponse(movieService.find(id));
        movieResponse.getDirectorResponse().setBirthYear(null);
        movieResponse.getDirectorResponse().setDeathYear(null);
        return Response.builder().data(movieResponse).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody MovieDto movieDTO) {
        /*Movie movie = movieService.create(MovieMapper.mapper.toMovie(movieCreateWeb));
        return Response.builder().data(MovieMapper.mapper.toMovieDetailWeb(movie)).build();*/
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") int id, @RequestBody MovieDto movieDTO) {
        /*movieUpdateWeb.setId(id);
        Movie movie = movieService.update(MovieMapper.mapper.toMovie(movieUpdateWeb));
        return Response.builder().data(MovieMapper.mapper.toMovieDetailWeb(movie)).build();*/
        return null;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        movieService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/characters")
    public Response addCharacterMovie(@PathVariable("id") int id, @RequestBody CharacterMovieDto characterMovieDTO){
        /*Movie movie = movieService.addCharacterMovie(id, CharacterMovieMapper.mapper.toCharacterMovie(characterMovieCreateWeb));
        return Response.builder().data(MovieMapper.mapper.toMovieDetailWeb(movie)).build();*/
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/characters/{characterId}")
    public Response updateCharacterMovie(@PathVariable("id") int id,@PathVariable("characterId") int characterId, @RequestBody CharacterMovieDto characterMovieDTO){
        /*characterMovieUpdateWeb.setId(characterId);
        Movie movie = movieService.updateCharacterMovie(id, CharacterMovieMapper.mapper.toCharacterMovie(characterMovieUpdateWeb));
        return Response.builder().data(MovieMapper.mapper.toMovieDetailWeb(movie)).build();*/
        return null;
    }


}
