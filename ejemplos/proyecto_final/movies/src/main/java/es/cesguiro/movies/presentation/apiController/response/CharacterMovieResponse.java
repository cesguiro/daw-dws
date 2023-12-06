package es.cesguiro.movies.presentation.apiController.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CharacterMovieResponse {

    private Integer id;
    private String characters;
    @JsonProperty("actor")
    private ActorResponse actorResponse;
}
