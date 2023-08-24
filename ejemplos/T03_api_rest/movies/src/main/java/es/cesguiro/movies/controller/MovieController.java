package es.cesguiro.movies.controller;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;
    private final int LIMIT = 10;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam Optional<Integer> page) {
        int totalRecords = movieService.getTotalNumberOfRecords();

        Response response = new Response(movieService.getAll(page), totalRecords, page, LIMIT);

        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Movie find(@PathVariable("id") int id) {
        return movieService.find(id);
    }
}
