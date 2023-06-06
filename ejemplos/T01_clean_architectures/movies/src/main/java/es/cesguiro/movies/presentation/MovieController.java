package es.cesguiro.movies.presentation;

import es.cesguiro.movies.business.entity.Movie;
import es.cesguiro.movies.business.service.MovieService;
import es.cesguiro.movies.business.service.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/movies")
@Controller
public class MovieController {

    //@Autowired
    private MovieService movieService = new MovieServiceImpl();

    @GetMapping("")
    public void getAll() {
        try {
            System.out.println(movieService.getAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public void find(@PathVariable("id") String id) {
        try {
            System.out.println(movieService.find(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
