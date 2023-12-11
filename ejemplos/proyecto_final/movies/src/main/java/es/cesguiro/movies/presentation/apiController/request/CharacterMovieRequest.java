package es.cesguiro.movies.presentation.apiController.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class CharacterMovieRequest {

    @Nullable
    Integer id;

    @Nullable
    @JsonProperty("actor")
    ActorRequest actorRequest;

    @Nullable
    String characters;

}
