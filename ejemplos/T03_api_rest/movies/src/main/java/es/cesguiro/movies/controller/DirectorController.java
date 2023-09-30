package es.cesguiro.movies.controller;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/directors")
@RestController
public class DirectorController {

    @Autowired
    DirectorService directorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Director create(@RequestBody Director director){
        int id = directorService.create(director);
        director.setId(id);
        return director;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Director update(@PathVariable("id") int id, @RequestBody Director director) {
        //Director existingDirector = directorService.find(id);
        //director.setId(id);
        //return directorService.update(director);
        return directorService.update(id, director);
    }


}
