package es.cesguiro.movies.controller;

import es.cesguiro.movies.domain.entity.Actor;
import es.cesguiro.movies.domain.service.ActorService;
import es.cesguiro.movies.domain.service.impl.ActorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/actors")
@Controller
public class ActorController {

    ActorService actorService = new ActorServiceImpl();

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
