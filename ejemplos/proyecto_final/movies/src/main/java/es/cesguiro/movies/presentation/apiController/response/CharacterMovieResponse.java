package es.cesguiro.movies.presentation.apiController.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CharacterMovieResponse {

    @JsonIgnore
    private Integer id;

    private String characters;

    @JsonProperty("actor")
    private ActorResponse actorResponse;
}
