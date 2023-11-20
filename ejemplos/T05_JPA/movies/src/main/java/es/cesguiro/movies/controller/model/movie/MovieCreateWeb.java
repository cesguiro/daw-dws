package es.cesguiro.movies.controller.model.movie;

import es.cesguiro.movies.controller.model.character.CharacterMovieCreateWeb;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class MovieCreateWeb {

    private String title;
    private int year;
    private int runtime;
    private int directorId;
    //private List<CharacterMovieCreateWeb> characterMovieCreateWebs;
    private Map<Integer, String> characters;
}
