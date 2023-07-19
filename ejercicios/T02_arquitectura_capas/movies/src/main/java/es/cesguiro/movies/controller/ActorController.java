package es.cesguiro.movies.controller;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.service.ActorService;
import es.cesguiro.movies.domain.service.impl.ActorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/actors")
@RestController
public class ActorController {

    @Autowired
    ActorService actorService;

    List<Actor> actors = List.of(
            new Actor("Robert Downey Jr.", 1968, null),
            new Actor("Chris Evans", 1981, null)
    );

    @GetMapping("/insert")
    public void create(){
        try {
            for (Actor actor: this.actors) {
                actorService.create(actor);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
