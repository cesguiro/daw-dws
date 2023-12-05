package es.cesguiro.movies.dto;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NonNull;

@Data
public class CharacterMovieDTO {

    @Nullable
    Integer id;

    @NonNull
    ActorDTO actorDTO;
    @NonNull
    String characters;
}
