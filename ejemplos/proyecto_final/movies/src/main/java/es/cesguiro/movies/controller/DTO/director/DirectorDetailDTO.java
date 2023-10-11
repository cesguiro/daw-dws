package es.cesguiro.movies.controller.DTO.director;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DirectorDetailDTO {

    int id;
    String name;
    int birthYear;
    int deathYear;
}
