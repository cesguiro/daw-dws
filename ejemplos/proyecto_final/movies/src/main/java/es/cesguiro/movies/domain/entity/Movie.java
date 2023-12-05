package es.cesguiro.movies.domain.entity;

import es.cesguiro.movies.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Objects;

public class Movie {

    private int id;
    private String title;
    private int year;
    private int runtime;
    private Director director;
    private List<CharacterMovie> characterMovies;

    public Movie(){

    }

    public Movie(int id, String title, int year, int runtime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }

    public Movie(String title, int year, int runtime) {
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<CharacterMovie> getCharacterMovies() {
        return characterMovies;
    }

    public void setCharacterMovies(List<CharacterMovie> characterMovies) {
        this.characterMovies = characterMovies;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", runtime=" + runtime +
                ", year=" + year +
                ", director=" + director +
                ", characters=" + characterMovies +
                '}';
    }

    public void addCharacterMovie(CharacterMovie characterMovie) {
        //posibles validaciones
        characterMovies.add(characterMovie);
    }

    public void updateCharacterMovie(CharacterMovie updatedCharacterMovie) {
        //posibles validaciones
        characterMovies.stream()
                .filter(characterMovie -> Objects.equals(characterMovie.getId(), updatedCharacterMovie.getId()))
                .findFirst()
                .ifPresentOrElse(characterMovie -> {
                    characterMovie.setActor(updatedCharacterMovie.getActor());
                    characterMovie.setCharacters(updatedCharacterMovie.getCharacters());
                }, () -> { throw new ResourceNotFoundException("Personaje no encontrado con id: " + updatedCharacterMovie.getId());
                        }
                );
    }
}
