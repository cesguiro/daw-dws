package es.cesguiro.movies.common.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.List;

@Data
public class MovieDto {

    @Nullable
    Integer id;
    @Nullable
    String title;
    @Nullable
    Integer year;
    @Nullable
    Integer runtime;

    @Nullable
    DirectorDto directorDto;
    @Nullable
    List<CharacterMovieDto> characterMovieDtoList;
}
