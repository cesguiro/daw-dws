package es.cesguiro.movies.controller;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;
    private final int pageSize = 10;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page) {
        //Response response = new Response(movieService.getAll(page), totalRecords, page, LIMIT);
        page = (page != null)? page : 1;
        Response response = new Response(movieService.getAll(page));
        //Response response = new Response();
        int totalRecords = movieService.getTotalNumberOfRecords();
        response.addAdditionalAttribute("total records", totalRecords);
        //if(page.isPresent()) {
            response.paginate(page, pageSize, totalRecords);
        //}
        //response.setData(movieService.getAll(page));

        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Movie find(@PathVariable("id") int id) {
        return movieService.find(id);
    }
}
