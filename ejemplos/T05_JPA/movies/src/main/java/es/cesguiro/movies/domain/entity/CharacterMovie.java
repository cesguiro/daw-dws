package es.cesguiro.movies.domain.entity;


import java.util.Arrays;

public class CharacterMovie {

    int id;
    Actor actor;
    String characters;

    public CharacterMovie(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    @Override
    public String toString() {
        return "CharacterMovie{" +
                "id=" + id +
                ", actor=" + actor +
                ", characters=" + characters +
                '}';
    }
}
