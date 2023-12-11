package es.cesguiro.movies.common.dto;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NonNull;

@Data
public class CharacterMovieDto {

    @Nullable
    Integer id;

    @NonNull
    ActorDto actorDto;

    @NonNull
    String characters;

}
