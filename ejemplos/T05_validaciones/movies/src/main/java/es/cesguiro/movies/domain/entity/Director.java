package es.cesguiro.movies.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import es.cesguiro.movies.domain.entity.VO.DirectorBirthYear;

public class Director {

    private int id;
    private String name;

    private DirectorBirthYear birthYear;
    //deathYear es de tipo Integer para poder pasarle nulos
    private Integer deathYear;

    public Director(){

    }

    public Director(int id, String name, int birthYear, Integer deathYear) {
        this.id = id;
        this.name = name;
        this.birthYear = new DirectorBirthYear(birthYear);
        this.deathYear = deathYear;
    }

    public Director(String name, int birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = new DirectorBirthYear(birthYear);
        this.deathYear = deathYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear.getYear();
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = new DirectorBirthYear(birthYear);
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                '}';
    }
}
