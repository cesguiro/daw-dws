package es.cesguiro.movies.presentation.apiController.request;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class DirectorRequest {

    @Nullable
    Integer id;
    @Nullable
    private String name;
    @Nullable
    private Integer birthYear;
    @Nullable
    private Integer deathYear;
}
