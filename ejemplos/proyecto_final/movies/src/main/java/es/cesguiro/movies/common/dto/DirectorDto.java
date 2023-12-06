package es.cesguiro.movies.common.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class DirectorDto {

    @Nullable
    Integer id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
}
