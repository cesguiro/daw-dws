package es.cesguiro.movies.common.dto;

import es.cesguiro.movies.common.dto.validation.ValidYear;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.List;

@Data
public class MovieDto {

    @Nullable
    Integer id;
    @Nullable
    String title;

    @Nullable
    @ValidYear
    Integer year;

    @Nullable
    @Min(value = 1, message = "La duración debe ser superior a 0 minutos")
    Integer runtime;

    @Nullable
    DirectorDto directorDto;

    @Nullable
    List<CharacterMovieDto> characterMovieDtoList;
}
