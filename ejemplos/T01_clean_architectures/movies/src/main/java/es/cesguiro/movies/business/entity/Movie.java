package es.cesguiro.movies.business.entity;

import java.util.List;

public class Movie {

    private String id;
    private String title;
    private int year;
    private int runTime;
    private Director director;
    private List<Actor> actors;

    public Movie(String id, String title, int runTime, int year) {
        this.id = id;
        this.title = title;
        this.runTime = runTime;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", runTime=" + runTime +
                ", year=" + year +
                ", director=" + director +
                ", actors=" + actors +
                '}';
    }
}
