package es.cesguiro.movies.presentation;

import es.cesguiro.movies.business.entity.Movie;
import es.cesguiro.movies.business.service.MovieService;
import es.cesguiro.movies.business.service.impl.MovieServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/movies")
@Controller
public class MovieController {

    private MovieService movieService = new MovieServiceImpl();

    @GetMapping("")
    public void getAll() {
        System.out.println(movieService.getAll());
    }
}
