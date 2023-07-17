package es.cesguiro.movies.controller;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.domain.service.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/movies")
//Si ponemos @Controller sale error Circular view path [preference]:
//Tiene que ver con la anotación @ResponseBody
@RestController
public class MovieController {

    //private MovieService movieService = new MovieServiceImpl();
    @Autowired
    private MovieService movieService;// = new MovieServiceImpl();

    @GetMapping("")
    public List<Movie> getAll() {
        try {
            System.out.println(movieService.getAll());
            return movieService.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @GetMapping("/{id}")
    public Movie find(@PathVariable("id") int id) {
        try {
            System.out.println(movieService.find(id));
            return movieService.find(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
