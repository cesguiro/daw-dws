package es.cesguiro.movies.controller.model.movie;

import es.cesguiro.movies.controller.model.character.CharacterCreateWeb;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieCreateWeb {

    private String title;
    private int year;
    private int runtime;
    private int directorId;
    private List<CharacterCreateWeb> characterCreateWebs;
}
