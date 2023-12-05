package es.cesguiro.movies.controller;

import es.cesguiro.movies.controller.model.character.CharacterMovieCreateWeb;
import es.cesguiro.movies.controller.model.character.CharacterMovieUpdateWeb;
import es.cesguiro.movies.controller.model.movie.MovieCreateWeb;
import es.cesguiro.movies.controller.model.movie.MovieDetailWeb;
import es.cesguiro.movies.controller.model.movie.MovieListWeb;
//import es.cesguiro.movies.controller.model.movie.MovieUpdateWeb;
import es.cesguiro.movies.controller.model.movie.MovieUpdateWeb;
import es.cesguiro.movies.controller.response.MovieResponse;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.dto.MovieDTO;
import es.cesguiro.movies.mapper.CharacterMovieMapper;
import es.cesguiro.movies.mapper.MovieMapper;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Stream;

import static es.cesguiro.movies.mapper.CharacterMovieMapper.mapper;

@RequestMapping(MovieController.MOVIES)
@RestController
public class MovieController {

    @Value("${application.url}")
    private String urlBase;

    public static final String MOVIES = "/movies";

    private final MovieService movieService;

    @Value("${page.size}")
    private int PAGE_SIZE;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : PAGE_SIZE;
        Stream<MovieDTO> movieDTOStream = (page != null)? movieService.getAll(page, pageSize) : movieService.getAll();

        Stream<MovieResponse> movieResponseStream =
                movieDTOStream.map(
                        movieDTO -> {
                            movieDTO.setYear(null);
                            movieDTO.setRuntime(null);
                            return MovieMapper.mapper.toMovieResponse(movieDTO);
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
        MovieResponse movieResponse = MovieMapper.mapper.toMovieResponse(movieService.find(id));
        return Response.builder().data(movieResponse).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody MovieCreateWeb movieCreateWeb) {
        Movie movie = movieService.create(MovieMapper.mapper.toMovie(movieCreateWeb));
        return Response.builder().data(MovieMapper.mapper.toMovieDetailWeb(movie)).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") int id, @RequestBody MovieUpdateWeb movieUpdateWeb) {
        movieUpdateWeb.setId(id);
        Movie movie = movieService.update(MovieMapper.mapper.toMovie(movieUpdateWeb));
        return Response.builder().data(MovieMapper.mapper.toMovieDetailWeb(movie)).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        movieService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/characters")
    public Response addCharacterMovie(@PathVariable("id") int id, @RequestBody CharacterMovieCreateWeb characterMovieCreateWeb){
        Movie movie = movieService.addCharacterMovie(id, CharacterMovieMapper.mapper.toCharacterMovie(characterMovieCreateWeb));
        return Response.builder().data(MovieMapper.mapper.toMovieDetailWeb(movie)).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/characters/{characterId}")
    public Response updateCharacterMovie(@PathVariable("id") int id,@PathVariable("characterId") int characterId, @RequestBody CharacterMovieUpdateWeb characterMovieUpdateWeb){
        characterMovieUpdateWeb.setId(characterId);
        Movie movie = movieService.updateCharacterMovie(id, CharacterMovieMapper.mapper.toCharacterMovie(characterMovieUpdateWeb));
        return Response.builder().data(MovieMapper.mapper.toMovieDetailWeb(movie)).build();
    }


}
