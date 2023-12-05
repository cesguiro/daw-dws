package es.cesguiro.movies.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class DirectorDTO {

    @Nullable
    Integer id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
}
