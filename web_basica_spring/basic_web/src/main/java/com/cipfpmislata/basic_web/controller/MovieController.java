package com.cipfpmislata.basic_web.controller;

import com.cipfpmislata.basic_web.domain.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


Actor actor1 = new Actor("al pacino", )
Actor actor2 = new Actor("robert de niro", )


@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping()
    public String getAll(){
        return movieService.getAll();
    }

    @GetMapping("/insert")
    public String insert(){
        return movieService.insert(actor1);
        return movieService.insert(actor2);
    }


    public void find(){
        System.out.println(movieService.find(2));
    }
}
