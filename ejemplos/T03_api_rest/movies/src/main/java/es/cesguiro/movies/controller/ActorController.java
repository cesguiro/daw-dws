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

}
