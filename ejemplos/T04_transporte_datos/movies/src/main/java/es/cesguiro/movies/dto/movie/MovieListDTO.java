package es.cesguiro.movies.dto.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieListDTO {

    @JsonIgnore
    private int id;
    private String title;
    private int year;
    private String link;

}
