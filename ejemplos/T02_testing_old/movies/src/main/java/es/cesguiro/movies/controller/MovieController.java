package es.cesguiro.movies.controller;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.domain.service.impl.MovieServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/movies")
@Controller
public class MovieController {

    //@Autowired
    private MovieService movieService = new MovieServiceImpl();

    /*private Movie movie = new Movie("Los vengadores", 2012, 143);
    private int director_id = 139;
    private List<Integer> actors_id = List.of(1770, 1771, 992, 1589);*/

    /*
    (?, ?, 'Tony Stark / Iron Man')
    (?, ?, 'Steve Rogers / Captain America')
    (?, 1589, 'Thor')
    (?, 992, 'Natasha Romanoff / Black Widow')
    */
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
