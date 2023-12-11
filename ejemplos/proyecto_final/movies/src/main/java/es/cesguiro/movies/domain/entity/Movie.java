package es.cesguiro.movies.domain.entity;

import es.cesguiro.movies.common.exception.ResourceNotFoundException;
import jakarta.validation.ValidationException;

import java.util.List;
import java.util.Objects;

public class Movie {

    private int id;
    private String title;
    private int year;
    private int runtime;
    private Director director;
    private List<CharacterMovie> characterMovieList;

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
        if(director.getBirthYear() != null && director.getBirthYear() > this.year) {
            throw new ValidationException("El año de nacimiento del director no puede ser mayor que el de la película.");
        }
        this.director = director;
    }

    public List<CharacterMovie> getCharacterMovieList() {
        return characterMovieList;
    }

    public void setCharacterMovieList(List<CharacterMovie> characterMovieList) {
        this.characterMovieList = characterMovieList;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", runtime=" + runtime +
                ", year=" + year +
                ", director=" + director +
                ", characters=" + characterMovieList +
                '}';
    }

    public void addCharacterMovie(CharacterMovie characterMovie) {
        if(characterMovie.getActor().getBirthYear() != null && characterMovie.getActor().getBirthYear() > this.year) {
            throw new ValidationException("El año de nacimiento del actor no puede ser mayor que el de la película.");
        }
        characterMovieList.add(characterMovie);
    }

    public void updateCharacterMovie(CharacterMovie updatedCharacterMovie) {
        if(updatedCharacterMovie.getActor().getBirthYear() != null && updatedCharacterMovie.getActor().getBirthYear() > this.year) {
            throw new ValidationException("El año de nacimiento del actor no puede ser mayor que el de la película.");
        }

        characterMovieList.stream()
                .filter(characterMovie -> Objects.equals(characterMovie.getId(), updatedCharacterMovie.getId()))
                .findFirst()
                .ifPresentOrElse(characterMovie -> {
                    characterMovie.setActor(updatedCharacterMovie.getActor());
                    characterMovie.setCharacters(updatedCharacterMovie.getCharacters());
                }, () -> { throw new ResourceNotFoundException("Personaje no encontrado con id: " + updatedCharacterMovie.getId());}
                );
    }

    public void deleteCharacterMovie(int characterMovieId) {
        if(this.characterMovieList.size() == 1) {
            throw new ValidationException("Una película tiene que tener, como mínimo, un personaje");
        }
        boolean removed = this.characterMovieList.removeIf(characterMovie -> characterMovie.getId() == characterMovieId);
        if(!removed) {
            throw new ResourceNotFoundException("Personaje no encontrado con id: " + characterMovieId);
        }
    }
}
