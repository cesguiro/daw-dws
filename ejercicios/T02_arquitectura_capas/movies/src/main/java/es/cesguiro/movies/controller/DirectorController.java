package es.cesguiro.movies.controller;

import es.cesguiro.movies.domain.entity.Director;
import es.cesguiro.movies.domain.service.DirectorService;
import es.cesguiro.movies.domain.service.impl.DirectorServiceImpl;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/directors")
@Controller
public class DirectorController {

    DirectorService directorService = new DirectorServiceImpl();

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
