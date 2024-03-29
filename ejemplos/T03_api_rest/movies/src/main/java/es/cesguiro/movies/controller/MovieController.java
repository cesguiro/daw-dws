package es.cesguiro.movies.controller;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;
    private final int PAGE_SIZE = 10;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page) {
        List<Movie> movies = (page != null)? movieService.getAll(page, PAGE_SIZE) : movieService.getAll();
        int totalRecords = movieService.getTotalNumberOfRecords();

        if(page != null) {
            return new Response(movies, totalRecords, page, PAGE_SIZE);
        } else {
            return new Response(movies, totalRecords);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return new Response(movieService.find(id));
    }
}
