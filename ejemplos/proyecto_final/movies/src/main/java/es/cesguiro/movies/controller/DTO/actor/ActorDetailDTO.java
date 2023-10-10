package es.cesguiro.movies.controller.DTO.actor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActorDetailDTO {

    int id;
    String name;
    int birthYear;
    int deathYear;
}
