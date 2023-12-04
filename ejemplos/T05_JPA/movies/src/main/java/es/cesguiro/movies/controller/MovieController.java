package es.cesguiro.movies.controller;

import es.cesguiro.movies.controller.model.movie.MovieCreateWeb;
import es.cesguiro.movies.controller.model.movie.MovieDetailWeb;
import es.cesguiro.movies.controller.model.movie.MovieListWeb;
//import es.cesguiro.movies.controller.model.movie.MovieUpdateWeb;
import es.cesguiro.movies.controller.model.movie.MovieUpdateWeb;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.mapper.MovieMapper;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        List<Movie> movies = (page != null)? movieService.getAll(page, pageSize) : movieService.getAll();
        List<MovieListWeb> moviesWeb = movies.stream()
                .map(MovieMapper.mapper::toMovieListWeb)
                .toList();
        long totalRecords = movieService.getTotalNumberOfRecords();
        Response response = Response.builder()
                .data(moviesWeb)
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
        Movie movie = movieService.find(id);
        MovieDetailWeb movieDetailWeb = MovieMapper.mapper.toMovieDetailWeb(movie);
        return Response.builder().data(movieDetailWeb).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody MovieCreateWeb movieCreateWeb) {
        Movie movie = movieService.create(MovieMapper.mapper.toMovie(movieCreateWeb));

        /*MovieListWeb movieListWeb = new MovieListWeb();
        movieListWeb.setTitle(movieCreateWeb.getTitle());
        movieListWeb.setId(id);*/
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
}
