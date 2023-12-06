package es.cesguiro.movies.common.dto;

import lombok.Data;

@Data
public class ActorDto {

    Integer id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
}
