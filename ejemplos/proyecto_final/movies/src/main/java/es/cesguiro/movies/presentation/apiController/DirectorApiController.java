package es.cesguiro.movies.presentation.apiController;

import es.cesguiro.movies.domain.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(DirectorApiController.DIRECTORS)
@RestController
public class DirectorApiController {

    public static final String DIRECTORS = "/api/directors";

    @Autowired
    DirectorService directorService;

    /*@Autowired
    MovieService movieService;*/

    /*@ResponseStatus(HttpStatus.CREATED)
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

    /*@ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return Response.builder().data(DirectorMapper.mapper.toDirectorDetailWeb(directorService.find(id))).build();
    }*/

    /*@ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/movies")
    public List<MovieListWeb> findMoviesByDirectorId(@PathVariable("id") int id) {
        return MovieMapper.mapper.toMovieListWebs(movieService.findByDirectorId(id));
    }*/

}
