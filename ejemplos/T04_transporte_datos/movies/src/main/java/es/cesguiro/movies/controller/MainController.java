package es.cesguiro.movies.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("")
    public String index(){
        return "Bienvenido a la API de películas";
    }
}
