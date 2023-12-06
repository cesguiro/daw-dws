package es.cesguiro.movies.presentation.apiController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(MainApiController.ROOT)
@RestController
public class MainApiController {

    public static final String ROOT = "/api";
    @GetMapping("")
    public String index(){
        return "Bienvenido a la API de películas";
    }
}
