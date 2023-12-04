package es.cesguiro.movies.domain.entity;


import lombok.Data;

import java.util.Arrays;

@Data
public class CharacterMovie {

    private int id;
    private Actor actor;
    private String characters;
}
