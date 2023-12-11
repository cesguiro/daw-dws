package es.cesguiro.movies.presentation.apiController.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.List;

@Data
public class MovieRequest {

    @Nullable
    Integer id;
    @Nullable
    String title;
    @Nullable
    Integer year;
    @Nullable
    Integer runtime;

    @Nullable
    @JsonProperty("director")
    DirectorRequest directorRequest;

    @Nullable
    @JsonProperty("characters")
    List<CharacterMovieRequest> characterMovieRequestList;
}
