package es.cesguiro.movies.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class DirectorResponse {

    private Integer id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer birthYear;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer deathYear;
}
