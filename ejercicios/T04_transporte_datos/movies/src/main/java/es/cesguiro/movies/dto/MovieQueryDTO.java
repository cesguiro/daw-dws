package es.cesguiro.movies.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieDTO {

    private int id;
    private String title;
    private int year;
    private int runtime;

    private DirectorDTO directorDTO;
    private List<ActorDTO> actorDTOs;
}
