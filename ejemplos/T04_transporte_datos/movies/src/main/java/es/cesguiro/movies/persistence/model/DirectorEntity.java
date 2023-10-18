package es.cesguiro.movies.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DirectorEntity {

    private int id;
    private String name;
    private int birthYear;
    private int deathYear;
}
