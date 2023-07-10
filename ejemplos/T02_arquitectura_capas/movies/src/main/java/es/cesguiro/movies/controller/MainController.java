package es.cesguiro.movies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String index(){
        return "Bienvenido a la API de películas";
    }

}
