package es.cesguiro.movies.controller;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Value("${page.size}")
    private int defaultPageSize;

    /*@ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        if(page == null)
            page = 1;
        if(pageSize == null) {
            pageSize = defaultPageSize;
        }
        int totalRecords = movieService.getTotalNumberOfRecords();
        Response response = new Response(movieService.getAll(page, pageSize), totalRecords, page, pageSize);
        return response;
    }*/

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        int totalRecords = movieService.getTotalNumberOfRecords();
        if(page != null && pageSize == null) {
            pageSize = defaultPageSize;
        }
        Response response = new Response(movieService.getAll(page, pageSize), totalRecords, page, pageSize);
        return response;
    }



    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        Response response = new Response(movieService.find(id), 1, null, null);
        return response;
    }
}
