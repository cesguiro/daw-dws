package es.cesguiro.movies.controller;

import es.cesguiro.movies.controller.model.director.DirectorCreateWeb;
import es.cesguiro.movies.controller.model.director.DirectorDetailWeb;
import es.cesguiro.movies.controller.model.director.DirectorUpdateWeb;
import es.cesguiro.movies.controller.model.movie.MovieListWeb;
import es.cesguiro.movies.domain.service.DirectorService;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.http_response.Response;
import es.cesguiro.movies.mapper.DirectorMapper;
import es.cesguiro.movies.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/directors")
@RestController
public class DirectorController {

    @Autowired
    DirectorService directorService;

    @Autowired
    MovieService movieService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody DirectorCreateWeb directorCreateWeb){
        int id = directorService.create(DirectorMapper.mapper.toDirector(directorCreateWeb));
        DirectorDetailWeb directorDetailWeb = new DirectorDetailWeb(
                id,
                directorCreateWeb.getName(),
                directorCreateWeb.getBirthYear(),
                directorCreateWeb.getDeathYear()
        );
        return Response.builder().data(directorDetailWeb).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody DirectorUpdateWeb directorUpdateWeb) {
        directorUpdateWeb.setId(id);
        directorService.update(DirectorMapper.mapper.toDirector(directorUpdateWeb));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        directorService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return Response.builder().data(DirectorMapper.mapper.toDirectorDetailWeb(directorService.find(id))).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/movies")
    public List<MovieListWeb> findMoviesByDirectorId(@PathVariable("id") int id) {
        return MovieMapper.mapper.toMovieListWebs(movieService.findByDirectorId(id));
    }

}
