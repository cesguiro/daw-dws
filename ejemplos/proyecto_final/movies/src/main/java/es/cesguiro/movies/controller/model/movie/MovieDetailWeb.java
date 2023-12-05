package es.cesguiro.movies.controller.model.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import es.cesguiro.movies.controller.model.character.CharacterMovieListWeb;
import es.cesguiro.movies.controller.model.director.DirectorListWeb;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieDetailWeb {

    private int id;
    private String title;
    private int year;
    private int runtime;
    private DirectorListWeb director;
    @JsonProperty("characters")
    private List<CharacterMovieListWeb> characterMovies;
}
