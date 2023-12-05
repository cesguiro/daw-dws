package es.cesguiro.movies.dto;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class MovieDTO {

    @Nullable
    Integer id;
    @Nullable
    String title;
    @Nullable
    Integer year;
    @Nullable
    Integer runtime;

    @Nullable
    DirectorDTO directorDTO;
    @Nullable
    List<CharacterMovieDTO> characterMovieDTOList;
}
