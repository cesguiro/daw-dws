package es.cesguiro.movies.controller;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.service.DirectorService;
import es.cesguiro.movies.domain.service.impl.DirectorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/directors")
@RestController
public class DirectorController {

    @Autowired
    DirectorService directorService;

    Director director = new Director("Joss Whedon", 1964, null);

    @GetMapping("/insert")
    public void create(){
        try {
            directorService.create(this.director);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
