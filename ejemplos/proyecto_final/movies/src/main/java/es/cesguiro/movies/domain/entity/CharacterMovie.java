package es.cesguiro.movies.domain.entity;


import lombok.Data;

import java.util.Objects;

@Data
public class CharacterMovie {

    private int id;
    private Actor actor;
    private String characters;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterMovie that = (CharacterMovie) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
