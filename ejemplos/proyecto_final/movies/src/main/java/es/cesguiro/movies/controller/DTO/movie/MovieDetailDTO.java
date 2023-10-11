package es.cesguiro.movies.controller.DTO.movie;

import es.cesguiro.movies.controller.DTO.actor.ActorDetailDTO;
import es.cesguiro.movies.controller.DTO.actor.ActorListDTO;
import es.cesguiro.movies.controller.DTO.director.DirectorDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetailDTO {

    private int id;
    private String title;
    private int year;
    private int runtime;

    DirectorDetailDTO director;
    List<ActorListDTO> actors;

    public MovieDetailDTO(int id, String title, int year, int runtime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }
}
