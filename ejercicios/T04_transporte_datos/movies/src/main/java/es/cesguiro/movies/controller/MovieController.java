package es.cesguiro.movies.controller;

import es.cesguiro.movies.controller.model.movie.MovieCreateWeb;
import es.cesguiro.movies.controller.model.movie.MovieListWeb;
import es.cesguiro.movies.dto.MovieDTO;
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

    @Autowired
    private MovieService movieService;

    @Value("${page.size}")
    private int PAGE_SIZE;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : PAGE_SIZE;
        List<MovieDTO> movieDTOs = (page != null)? movieService.getAll(page, pageSize) : movieService.getAll();
        List<MovieListWeb> moviesWeb = movieDTOs.stream()
                .map(MovieMapper.mapper::toMovieListWeb)
                .toList();
        int totalRecords = movieService.getTotalNumberOfRecords();
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
        return Response.builder()
                .data(MovieMapper.mapper.toMovieDetailWeb(movieService.find(id)))
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody MovieCreateWeb movieCreateWebCreateWeb) {
        int id = movieService.create(
                MovieMapper.mapper.toMovieDTO(movieCreateWebCreateWeb),
                movieCreateWebCreateWeb.getDirectorId(),
                movieCreateWebCreateWeb.getActorIds()
        );
        MovieListWeb movieListWeb = new MovieListWeb();
        movieListWeb.setTitle(movieCreateWebCreateWeb.getTitle());
        movieListWeb.setId(id);
        return Response.builder().data(movieListWeb).build();
    }

}
