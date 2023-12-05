package es.cesguiro.movies.dto;

import lombok.Data;

@Data
public class ActorDTO {

    Integer id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
}
