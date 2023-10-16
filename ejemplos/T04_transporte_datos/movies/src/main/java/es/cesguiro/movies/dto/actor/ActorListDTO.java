package es.cesguiro.movies.dto.actor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActorListDTO {

    @JsonIgnore
    private int id;
    private String name;
    private String link;
}
