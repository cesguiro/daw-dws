package es.cesguiro.movies.controller.model.character;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharacterCreateWeb {

    int actorId;
    String[] characters;
}