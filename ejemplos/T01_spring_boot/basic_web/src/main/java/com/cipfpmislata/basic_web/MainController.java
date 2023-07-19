package com.cipfpmislata.basic_web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MainController {

    @GetMapping("/")
    public void index() {
        System.out.println("Método index del controlador Main ejecutándose");
    }

    @GetMapping("/about")
    public void about(){
        System.out.println("Método about de MainController ejecutándose");
    }
}
