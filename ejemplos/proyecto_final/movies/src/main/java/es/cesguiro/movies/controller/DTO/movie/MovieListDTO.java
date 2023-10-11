package es.cesguiro.movies.controller.DTO.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieListDTO {

    private int id;
    private String title;
    private int year;
    private int runtime;


}
