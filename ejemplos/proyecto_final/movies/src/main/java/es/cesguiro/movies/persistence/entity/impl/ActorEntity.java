package es.cesguiro.movies.persistence.entity.impl;

import es.cesguiro.movies.persistence.entity.GenericEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActorEntity extends GenericEntity {

    protected String table = "actors";

    private int id;
    private String name;
    private int birthYear;
    private int deathYear;

    public ActorEntity(int id, String name, int birthYear, int deathYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

}
