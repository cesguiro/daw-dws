package es.cesguiro.movies.persistence.entity.impl;

import es.cesguiro.movies.persistence.entity.GenericEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieEntity extends GenericEntity {

    protected String table = "movies";

    private int id;
    private String title;
    private int year;
    private int runtime;

    public MovieEntity(int id, String title, int year, int runtime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }
}
