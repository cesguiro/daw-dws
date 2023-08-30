package es.cesguiro.movies.controller;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${page.size}")
    private int LIMIT;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize) {
        int limit = (pageSize.isPresent())? pageSize.get(): LIMIT;
        int totalRecords = movieService.getTotalNumberOfRecords();

        Response response = new Response(movieService.getAll(page, Optional.of(limit)), totalRecords, page, limit);

        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Movie find(@PathVariable("id") int id) {
        return movieService.find(id);
    }
}
