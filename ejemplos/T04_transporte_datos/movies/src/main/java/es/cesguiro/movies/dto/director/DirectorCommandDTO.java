package es.cesguiro.movies.dto.director;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class DirectorCommandDTO {

    private String name;
    private int birthYear;
    private Integer deathYear;
}
