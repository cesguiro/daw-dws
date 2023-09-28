package es.cesguiro.movies.controller;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/actors")
@RestController
public class ActorController {
    @Autowired
    ActorService actorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Actor create(@RequestBody Actor actor){
        int id = actorService.create(actor);
        actor.setId(id);
        return actor;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Actor actor) {
        actorService.update(id, actor);
    }


}
